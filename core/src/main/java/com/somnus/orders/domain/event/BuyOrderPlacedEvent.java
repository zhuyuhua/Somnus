package com.somnus.orders.domain.event;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.OrderId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class BuyOrderPlacedEvent extends AbstractOrderPlacedEvent {

	public BuyOrderPlacedEvent(OrderBookId orderBookId, OrderId orderId,
			TransactionId transactionId, long tradeCount, long itemPrice,
			PortfolioId portfolioId) {
		super(orderBookId, orderId, transactionId, tradeCount, itemPrice,
				portfolioId);
	}

}
