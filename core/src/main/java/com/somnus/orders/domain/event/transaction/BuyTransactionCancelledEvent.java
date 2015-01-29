package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class BuyTransactionCancelledEvent extends AbstractTransactionCancelledEvent {

    public BuyTransactionCancelledEvent(TransactionId transactionIdentifier, long totalAmountOfItems, long amountOfExecutedItems) {
        super(transactionIdentifier, totalAmountOfItems, amountOfExecutedItems);
    }
}
