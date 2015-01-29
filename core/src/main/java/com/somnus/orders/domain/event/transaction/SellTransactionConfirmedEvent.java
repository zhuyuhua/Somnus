package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class SellTransactionConfirmedEvent extends AbstractTransactionConfirmedEvent {

    public SellTransactionConfirmedEvent(TransactionId transactionIdentifier) {
        super(transactionIdentifier);
    }

}
