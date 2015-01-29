package com.somnus.orders.command;

import com.somnus.orders.domain.vo.OrderBookId;

public class CreateOrderBookCommand {

	 private OrderBookId orderBookId;

	    public CreateOrderBookCommand(OrderBookId orderBookId) {
	        this.orderBookId = orderBookId;
	    }

	    public OrderBookId getOrderBookIdentifier() {
	        return this.orderBookId;
	    }
	    
}
