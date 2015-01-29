package com.somnus.portfolio.domain.event.cash;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class CashReservedEvent {
    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    private long amountToReserve;

    public CashReservedEvent(PortfolioId portfolioIdentifier, TransactionId transactionIdentifier, long amountToReserve) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountToReserve = amountToReserve;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public long getAmountToReserve() {
        return amountToReserve;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
