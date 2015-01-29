package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractTransactionStartedEvent {
    private TransactionId transactionIdentifier;
    private OrderBookId orderbookIdentifier;
    private PortfolioId portfolioIdentifier;
    private long totalItems;
    private long pricePerItem;

    public AbstractTransactionStartedEvent(TransactionId transactionIdentifier,
                                           OrderBookId orderbookIdentifier,
                                           PortfolioId portfolioIdentifier,
                                           long totalItems,
                                           long pricePerItem) {
        this.transactionIdentifier = transactionIdentifier;
        this.orderbookIdentifier = orderbookIdentifier;
        this.portfolioIdentifier = portfolioIdentifier;
        this.totalItems = totalItems;
        this.pricePerItem = pricePerItem;
    }

    public OrderBookId getOrderbookIdentifier() {
        return orderbookIdentifier;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public long getPricePerItem() {
        return pricePerItem;
    }

    public long getTotalItems() {
        return totalItems;
    }
}
