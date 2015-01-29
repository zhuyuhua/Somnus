package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class BuyTransactionExecutedEvent extends AbstractTransactionExecutedEvent {

    public BuyTransactionExecutedEvent(TransactionId transactionIdentifier, long amountOfItems, long itemPrice) {
        super(transactionIdentifier, amountOfItems, itemPrice);
    }
}
