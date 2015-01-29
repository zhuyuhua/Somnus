package com.somnus.orders.command;

import javax.validation.constraints.Min;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.OrderId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractOrderCommand {

	private PortfolioId portfolioId;
    @TargetAggregateIdentifier
    private OrderBookId orderBookId;
    private TransactionId transactionId;
    @Min(0)
    private long tradeCount;
    @Min(0)
    private long itemPrice;
    private OrderId orderId;

    protected AbstractOrderCommand(OrderId orderId, PortfolioId portfolioId, OrderBookId orderBookId,
                                   TransactionId transactionId, long tradeCount, long itemPrice) {
        this.portfolioId = portfolioId;
        this.orderBookId = orderBookId;
        this.tradeCount = tradeCount;
        this.itemPrice = itemPrice;
        this.transactionId = transactionId;
        this.orderId = orderId;
    }

    public PortfolioId getPortfolioId() {
        return portfolioId;
    }

    public OrderBookId getOrderBookId() {
        return orderBookId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public long getTradeCount() {
        return tradeCount;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public OrderId getOrderId() {
        return orderId;
    }
	
}
