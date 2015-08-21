/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.rpc.netty.example.discard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class DiscardClientHandler  extends SimpleChannelInboundHandler<Object>{

	private static Logger logger = LoggerFactory.getLogger(DiscardClientHandler.class);

	  private ByteBuf content;
	  private ChannelHandlerContext ctx;

	  @Override
	  public void channelActive(ChannelHandlerContext ctx) {
	      this.ctx = ctx;

	      // Initialize the message.
	      content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);

	      // Send the initial messages.
	      generateTraffic();
	  }

	  @Override
	  public void channelInactive(ChannelHandlerContext ctx) {
	      content.release();
	  }

	  @Override
	  public void messageReceived(ChannelHandlerContext ctx, Object msg) {
	      // Server is supposed to send nothing, but if it sends something, discard it.
	  }

	  @Override
	  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	      // Close the connection when an exception is raised.
	      cause.printStackTrace();
	      ctx.close();
	  }

	  long counter;

	  private void generateTraffic() {
	      // Flush the outbound buffer to the socket.
	      // Once flushed, generate the same amount of traffic again.
	      ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
	  }

	  private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
	      @Override
	      public void operationComplete(ChannelFuture future) {
	          if (future.isSuccess()) {
	              generateTraffic();
	          } else {
	              future.cause().printStackTrace();
	              future.channel().close();
	          }
	      }
	  };
}
