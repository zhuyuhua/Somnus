package com.somnus.orders.command.transaction;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractStartTransactionCommand {

    private TransactionId transactionId;
    private OrderBookId orderbookIdentifier;
    private PortfolioId portfolioIdentifier;
    private long tradeCount;
    private long itemPrice;

    public AbstractStartTransactionCommand(TransactionId transactionId, OrderBookId orderbookIdentifier,
                                           PortfolioId portfolioIdentifier, long tradeCount, long itemPrice) {
        this.transactionId = transactionId;
        this.itemPrice = itemPrice;
        this.orderbookIdentifier = orderbookIdentifier;
        this.portfolioIdentifier = portfolioIdentifier;
        this.tradeCount = tradeCount;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public OrderBookId getOrderbookIdentifier() {
        return orderbookIdentifier;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionId;
    }

    public long getTradeCount() {
        return tradeCount;
    }
}
