package com.somnus.orders.command.transaction;

import com.somnus.orders.domain.vo.TransactionId;

public class ConfirmTransactionCommand {

    private TransactionId transactionIdentifier;

    public ConfirmTransactionCommand(TransactionId transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
