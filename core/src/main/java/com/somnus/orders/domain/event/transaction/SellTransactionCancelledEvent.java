package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class SellTransactionCancelledEvent extends AbstractTransactionCancelledEvent {

    public SellTransactionCancelledEvent(TransactionId transactionIdentifier, long totalAmountOfItems, long amountOfExecutedItems) {
        super(transactionIdentifier, totalAmountOfItems, amountOfExecutedItems);
    }
}
