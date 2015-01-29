package com.somnus.orders.command.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class ExecutedTransactionCommand {

    private TransactionId transactionIdentifier;
    private long amountOfItems;
    private long itemPrice;

    public ExecutedTransactionCommand(TransactionId transactionIdentifier, long amountOfItems, long itemPrice) {
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfItems = amountOfItems;
        this.itemPrice = itemPrice;
    }

    public long getAmountOfItems() {
        return amountOfItems;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
