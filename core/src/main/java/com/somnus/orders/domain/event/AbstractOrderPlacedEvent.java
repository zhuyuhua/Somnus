package com.somnus.orders.domain.event;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.OrderId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractOrderPlacedEvent {

	private final OrderBookId orderBookId;
    private final OrderId orderId;
    private TransactionId transactionId;
    private final long tradeCount;
    private final long itemPrice;
    private final PortfolioId portfolioId;

    protected AbstractOrderPlacedEvent(OrderBookId orderBookId, OrderId orderId, TransactionId transactionId,
                                       long tradeCount, long itemPrice, PortfolioId portfolioId) {
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.tradeCount = tradeCount;
        this.itemPrice = itemPrice;
        this.portfolioId = portfolioId;
        this.orderBookId = orderBookId;
    }

    public OrderBookId orderBookIdentifier() {
        return this.orderBookId;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public long getTradeCount() {
        return tradeCount;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public PortfolioId getPortfolioId() {
        return portfolioId;
    }
}
