package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class BuyTransactionConfirmedEvent extends AbstractTransactionConfirmedEvent {

    public BuyTransactionConfirmedEvent(TransactionId transactionIdentifier) {
        super(transactionIdentifier);
    }
}
