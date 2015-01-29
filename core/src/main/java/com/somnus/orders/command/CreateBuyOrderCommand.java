package com.somnus.orders.command;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.OrderId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class CreateBuyOrderCommand extends AbstractOrderCommand {

	public CreateBuyOrderCommand(OrderId orderId, PortfolioId portfolioId,
			OrderBookId orderBookId, TransactionId transactionId,
			long tradeCount, long itemPrice) {
		super(orderId, portfolioId, orderBookId, transactionId, tradeCount,
				itemPrice);
	}

}
