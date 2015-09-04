package com.somnus.rpc.netty.example.aio;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler
		implements
		CompletionHandler<AsynchronousServerSocketChannel, AsyncTimeServerHandler> {

	@Override
	public void completed(AsynchronousServerSocketChannel result,
			AsyncTimeServerHandler attachment) {
		// attachment.asynchronousServerSocketChannel.accept(attachment, this);
	}

	@Override
	public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
		// TODO Auto-generated method stub

	}

}
