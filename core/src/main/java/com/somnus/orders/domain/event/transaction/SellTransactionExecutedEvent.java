package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class SellTransactionExecutedEvent extends AbstractTransactionExecutedEvent {

    public SellTransactionExecutedEvent(TransactionId transactionIdentifier, long amountOfItems, long itemPrice) {
        super(transactionIdentifier, amountOfItems, itemPrice);
    }
}
