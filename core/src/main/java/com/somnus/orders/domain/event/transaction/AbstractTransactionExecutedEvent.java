package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractTransactionExecutedEvent {
	
    private TransactionId transactionIdentifier;
    private long amountOfItems;
    private long itemPrice;

    public AbstractTransactionExecutedEvent(TransactionId transactionIdentifier, long amountOfItems, long itemPrice) {
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfItems = amountOfItems;
        this.itemPrice = itemPrice;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public long getAmountOfItems() {
        return amountOfItems;
    }

    public long getItemPrice() {
        return itemPrice;
    }
}
