## Kafka ##

http://blog.csdn.net/zhongwen7710/article/details/41252649
http://blog.csdn.net/lizhitao/article/details/39499283

## 二、HA(高可用)


### 1、Data Replication

#### 1）Replica均匀分布到整个集群

Kafka分配Replica的算法如下：	

1. 将所有Broker（假设共n个Broker）和待分配的Partition排序
2. 将第i个Partition分配到第（i mod n）个Broker上
3. 将第i个Partition的第j个Replica分配到第（(i + j) mode n）个Broker上

例子：

3个Broker：broker1，broker2，broker3

20个Partition：p1,p2,p3,...,p20

每个Partition拥有1个Replica(r1,r2,...,r20)。

那么分配如下：

	- broker1：p1,p4,p7,p10,p13,p16,p19
	- broker2：p2,p5,p8,p11,p14,p17,p20
	- broker3：p3,p6,p9,p12,p15,p18,

### 2、Leader Election


## 三、源码解析
### 1.ProducerRecord类

1. ProducerRecord 含义: 发送给Kafka Broker的key/value 值对
2. 内部数据结构：

	- Topic （名字）
	
	- PartitionID ( 可选)
	
	- Key[( 可选 )
	
	- Value


	<1> 若指定Partition ID,则PR被发送至指定Partition
	
	<2> 若未指定Partition ID,但指定了Key, PR会按照hasy(key)发送至对应Partition
	
	<3> 若既未指定Partition ID也没指定Key，PR会按照round-robin模式（轮询模式）发送到每个Partition
	
	<4> 若同时指定了Partition ID和Key, PR只会发送到指定的Partition (Key不起作用，代码逻辑决定)

3.三种构造函数形参:

- ProducerRecord(topic, partition, key, value)
- ProducerRecord(topic, key, value)
- ProducerRecord(topic, value) 


### 2.JAVA API

1.创建topic

【命令方式】：bin/kafka-topics.sh --zookeeper zk_host:port/chroot --create --topic my_topic_name --partitions 20 --replication-factor 3 --config x=y

【JAVA API方式】：

    String[] options = new String[]{  
	    "--create",  
	    "--zookeeper",  
	    "zk_host:port/chroot",  
	    "--partitions",  
	    "20",  
	    "--topic",  
	    "my_topic_name",  
	    "--replication-factor",  
	    "3",  
	    "--config",  
	    "x=y"  
	};  
	TopicCommand.main(options);  