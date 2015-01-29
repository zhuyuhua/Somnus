package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public abstract class AbstractTransactionConfirmedEvent {
    private TransactionId transactionIdentifier;

    protected AbstractTransactionConfirmedEvent(TransactionId transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
