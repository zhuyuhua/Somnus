package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractTransactionPartiallyExecutedEvent {
    private TransactionId transactionIdentifier;
    private long amountOfExecutedItems;
    private long totalOfExecutedItems;
    private long itemPrice;

    public AbstractTransactionPartiallyExecutedEvent(TransactionId transactionIdentifier, long amountOfExecutedItems, long totalOfExecutedItems,
                                                     long itemPrice) {
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfExecutedItems = amountOfExecutedItems;
        this.totalOfExecutedItems = totalOfExecutedItems;
        this.itemPrice = itemPrice;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public long getAmountOfExecutedItems() {
        return amountOfExecutedItems;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public long getTotalOfExecutedItems() {
        return totalOfExecutedItems;
    }
}
