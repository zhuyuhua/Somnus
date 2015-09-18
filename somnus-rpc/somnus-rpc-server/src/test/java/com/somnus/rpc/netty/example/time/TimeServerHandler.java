package com.somnus.rpc.netty.example.time;

import java.sql.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	/*
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see
	 * io.netty.channel.ChannelHandlerAdapter#channelRegistered(io.netty.channel
	 * .ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("注册成功..." + ctx.name());
		ctx.fireChannelRegistered();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Channel正在监听..." + ctx.name());
		ctx.writeAndFlush("send msg from server..");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("服务器读取到客户端请求:" + body);
		String curentTime = new Date(System.currentTimeMillis()).toString();
		ByteBuf resp = Unpooled.copiedBuffer(curentTime.getBytes());
		ctx.write(resp);
		System.out.println("服务器做出了响应:" + curentTime);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
        System.out.println("服务器readComplete 响应完成");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("服务器异常退出:" + cause.getMessage());
	}

}
