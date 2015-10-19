# Sendfile #

### 背景

apache，nginx，lighttpd等web服务器当中，都有一项sendfile相关的配置，用于提升文件传输性能，下面分析一下sendfile为什么会提升性能。

### 演变过程

传统的文件传输里面（read/write方式），在实现上其实是比较复杂的，需要经过多次上下文的切换，我们看一下如下两行代码：

    read(file, tmp_buf, len);       
	write(socket, tmp_buf, len);

 以上两行代码是传统的read/write方式进行文件到socket的传输。

当需要对一个文件进行传输的时候，其具体流程细节如下：

1. 调用read函数，文件数据被copy到内核缓冲区
2. read函数返回，文件数据从内核缓冲区copy到用户缓冲区
3. write函数调用，将文件数据从用户缓冲区copy到内核与socket相关的缓冲区。
4. 数据从socket缓冲区copy到相关协议引擎。


以上是传统read/write方式进行网络文件传输的方式，我们可以看到，在这个过程当中，文件数据实际上是经过了四次copy操作：

**硬盘—>内核buf—>用户buf—>socket相关缓冲区—>协议引擎**

sendfile系统调用则提供了一种减少以上多次copy，提升文件传输性能的方法。Sendfile系统调用是在2.1版本内核时引进的：

    sendfile(socket, file, len); 

运行流程如下：

1. sendfile系统调用，文件数据被copy至内核缓冲区
2. 再从内核缓冲区copy至内核中socket相关的缓冲区
3. 最后再socket相关的缓冲区copy到协议引擎

经过了三次copy操作：

**硬盘—>内核buf—>socket相关缓冲区—>协议引擎**

2.4版本内核再次对文件传输做了优化：

sendfile实现了更简单的方式，系统调用方式仍然一样，细节与2.1版本的 不同之处在于，当文件数据被复制到内核缓冲区时，不再将所有数据copy到socket相关的缓冲区，而是仅仅将记录数据位置和长度相关的数据保存到 socket相关的缓存，而实际数据将由DMA模块直接发送到协议引擎，再次减少了一次copy操作。

也就是：

**硬盘—>内核buf—>协议引擎**

### 源码解读

Sendfile函数说明

	#include 
	ssize_t sendfile(int out_fd, int in_fd, off_t *offset, size_t count);
	

sendfile()是作用于数据拷贝在两个文件描述符之间的操作函数.这个拷贝操作是内核中操作的,所以称为"零拷贝".sendfile函数比起read和write函数高效得多,因为read和write是要把数据拷贝到用户应用层操作.

参数说明:
out_fd 是已经打开了,用于写操作(write)的文件描述符;
in_fd 是已经打开了,用于读操作(read)的文件描述符;
offset 偏移量;表示sendfile函数从in_fd中的哪一偏移量开始读取数据.如果是零表示从文件的开始读,否则从相应的便宜量读取.如果是循环读取的时候,下一次offset值应为sendfile函数返回值加上本次的offset的值.
count是在两个描述符之间拷贝的字节数(bytes)

返回值:
如果成功的拷贝,返回写操作到out_fd的字节数,错误返回-1,并相应的设置error信息.