# Nio原理 #

## 概括
NIO的包中主要包含了这样几种抽象数据类型：


- Buffer：包含数据且用于读写的线形表结构。其中还提供了一个特殊类用于内存映射文件的I/O操作。
- Charset：它提供Unicode字符串影射到字节序列以及逆映射的操作。
- Channels：包含socket，file和pipe三种管道，都是全双工的通道。
- Selector：多个异步I/O操作集中到一个或多个线程中（可以被看成是Unix中select()函数的面向对象版本）。


### 1.Selector机制

#### 示例

代码范例：

    public static void main(String[] args) {
		int maxsize = 65535;
		Selector[] selectors = new Selector[maxsize];
		try {
			for (int i = 0; i < maxsize; i++) {
				selectors[i] = Selector.open();
			}
			Thread.sleep(3000000);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

报错：

	java.io.IOException: Unable to establish loopback connection
		at sun.nio.ch.PipeImpl$Initializer.run(PipeImpl.java:125)
		at sun.nio.ch.PipeImpl$Initializer.run(PipeImpl.java:69)
		at java.security.AccessController.doPrivileged(Native Method)
		at sun.nio.ch.PipeImpl.<init>(PipeImpl.java:141)
		at sun.nio.ch.SelectorProviderImpl.openPipe(SelectorProviderImpl.java:50)
		at java.nio.channels.Pipe.open(Pipe.java:150)
		at sun.nio.ch.WindowsSelectorImpl.<init>(WindowsSelectorImpl.java:127)
		at sun.nio.ch.WindowsSelectorProvider.openSelector(WindowsSelectorProvider.java:44)
		at java.nio.channels.Selector.open(Selector.java:227)
		at com.somnus.jdk.nio.TestSelector.main(TestSelector.java:40)
	Caused by: java.net.SocketException: No buffer space available (maximum connections reached?): connect

分析原因：

1. Windows下，Selector.open()会自己和自己建立两条TCP链接。不但消耗了两个TCP连接和端口，同时也消耗了文件描述符。
2. Linux下，Selector.open()会自己和自己建两条管道。同样消耗了两个系统的文件描述符。

在Windows下，Sun的JVM之所以选择TCP连接，而不是Pipe，要么是因为性能的问题，要么是因为资源的问题。可能，Windows下的管道的性能要慢于TCP链接，也有可能是Windows下的管道所消耗的资源会比TCP链接多。总而言之：Java的Selector在不同平台上的机制。


### Java为什么要如此消耗资源？

1.线程阻塞在Selector.select()方法中，只需要调用Selector的wakeup()方法。我们知道要想唤醒线程，有以下方法：
	
  	1）  有数据可读/写，或出现异常。
	2）  阻塞时间到，即time out。
	3）  收到一个non-block的信号。可由kill或pthread_kill发出。

因此Selector.wakeup()也是通过这三种方法。

1）第二种方法可以排除，因为select一旦阻塞，应无法修改其time out时间。

2）而第三种看来只能在Linux上实现，Windows上没有这种信号通知的机制。

所以只能通过第1种方法。
回到一开始的Selector.open()，在windows下，会建立两条自己对自己的循环监听的TCP连接，在linux上会开一对pipe（linux下的pipe一般都是成对打开）。
由此可以猜到：如果想要唤醒select，只需要朝着自己的这个loopback连接发点数据过去，就可以唤醒阻塞在select上的线程了。

selector的notify和wakeup这两个方法完全是来模仿linux中的的kill和pthread_kill给阻塞在select上的线程发信号的。

但因为发信号这个东西并不是一个跨平台的标准（pthread_kill这个系统调用也不是所有unix/linux都支持的），而pipe是所有的unix/linux所支持的，但windows又不支持，所以，windows用了tcp连接来实现这个。
