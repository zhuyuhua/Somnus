package com.somnus.orders.command.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class CancelTransactionCommand {

    private TransactionId transactionIdentifier;

    public CancelTransactionCommand(TransactionId transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
