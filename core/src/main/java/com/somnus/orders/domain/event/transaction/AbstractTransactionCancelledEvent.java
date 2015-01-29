package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractTransactionCancelledEvent {
    private TransactionId transactionIdentifier;
    private long totalAmountOfItems;
    private long amountOfExecutedItems;

    public AbstractTransactionCancelledEvent(TransactionId transactionIdentifier, long totalAmountOfItems, long amountOfExecutedItems) {
        this.transactionIdentifier = transactionIdentifier;
        this.totalAmountOfItems = totalAmountOfItems;
        this.amountOfExecutedItems = amountOfExecutedItems;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public long getAmountOfExecutedItems() {
        return amountOfExecutedItems;
    }

    public long getTotalAmountOfItems() {
        return totalAmountOfItems;
    }
}
