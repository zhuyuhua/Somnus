package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class SellTransactionPartiallyExecutedEvent extends AbstractTransactionPartiallyExecutedEvent {

    public SellTransactionPartiallyExecutedEvent(TransactionId transactionIdentifier,
                                                 long amountOfExecutedItems,
                                                 long totalOfExecutedItems,
                                                 long itemPrice) {
        super(transactionIdentifier, amountOfExecutedItems, totalOfExecutedItems, itemPrice);
    }
}
