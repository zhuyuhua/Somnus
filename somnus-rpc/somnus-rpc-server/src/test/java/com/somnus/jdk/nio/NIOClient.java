/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.somnus.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.utils.date.DateTimeUtil;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月28日
 */
public class NIOClient {

	private static Logger logger = LoggerFactory.getLogger(NIOClient.class);

	//通道管理器  
    private Selector selector;  
  
    /** 
     * 获得一个Socket通道，并对该通道做一些初始化的工作 
     * @param ip 连接的服务器的ip 
     * @param port  连接的服务器的端口号          
     * @throws IOException 
     */  
	public void initClient(String ip, int port) throws Exception {
        // 获得一个Socket通道  
        SocketChannel channel = SocketChannel.open();  
        // 设置通道为非阻塞  
        channel.configureBlocking(false);  
        // 获得一个通道管理器  
        this.selector = Selector.open();  
          
        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调  
        //用channel.finishConnect();才能完成连接  
        channel.connect(new InetSocketAddress(ip,port));  
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。  
        channel.register(selector, SelectionKey.OP_CONNECT);  
    }  
  
    /** 
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理 
     * @throws IOException 
     */  
    @SuppressWarnings("unchecked")  
	public void listen() throws Exception {
        // 轮询访问selector  
        while (true) {  
            selector.select();  
            // 获得selector中选中的项的迭代器  
			Set<SelectionKey> selectKeys = selector.selectedKeys();
			Iterator<SelectionKey> ite = selectKeys.iterator();

            while (ite.hasNext()) {  
                SelectionKey key = ite.next();  
                // 删除已选的key,以防重复处理  
                ite.remove();  
                // 连接事件发生  
                if (key.isConnectable()) {  
                    SocketChannel channel = (SocketChannel) key  
                            .channel();  
                    // 如果正在连接，则完成连接  
                    if(channel.isConnectionPending()){  
                        channel.finishConnect();  
                          
                    }  
                    // 设置成非阻塞  
                    channel.configureBlocking(false);  
  
                    //在这里可以给服务端发送信息哦  
					channel.write(ByteBuffer.wrap(("发消息给客户端：" + DateTimeUtil.getFullTodayStr()).getBytes("utf-8")));
                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。  
                    channel.register(this.selector, SelectionKey.OP_READ);  
                      
                    // 获得了可读的事件  
                } else if (key.isReadable()) {  
					// read(key);
                }  
  
            }  
  
        }  
    }  
    
	/**
	 * 处理读取服务端发来的信息 的事件
	 * 
	 * @param key
	 * @throws Exception
	 */  
	public void read(SelectionKey key) throws Exception {
    	// 服务器可读取消息:得到事件发生的Socket通道  
        SocketChannel channel = (SocketChannel) key.channel();  
        // 创建读取的缓冲区  
		ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);  
        byte[] data = buffer.array();  
		String msg = new String(data, "utf-8").trim();
		logger.debug("客户端收到信息：" + msg);
		Thread.sleep(5000);
		msg = DateTimeUtil.getFullTodayStr();
		logger.debug("发给服务端：" + msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes("utf-8"));
        channel.write(outBuffer);// 将消息回送给客户端
    }  
      
      
    /** 
     * 启动客户端测试 
     * @throws IOException  
     */  
	public static void main(String[] args) throws Exception {
        NIOClient client = new NIOClient();  
        client.initClient("localhost",8000);  
        client.listen();  
    }  
}
