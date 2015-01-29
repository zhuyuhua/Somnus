package com.somnus.orders.domain.event;

import com.somnus.orders.domain.vo.OrderBookId;

public class OrderBookCreatedEvent {

	private OrderBookId orderBookId;

    public OrderBookCreatedEvent(OrderBookId orderBookId) {
        this.orderBookId = orderBookId;
    }

    public OrderBookId getOrderBookIdentifier() {
        return this.orderBookId;
    }
    
}
