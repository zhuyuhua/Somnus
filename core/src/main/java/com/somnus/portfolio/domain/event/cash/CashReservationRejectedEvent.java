package com.somnus.portfolio.domain.event.cash;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class CashReservationRejectedEvent {
    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    private long amountToPayInCents;

    public CashReservationRejectedEvent(PortfolioId portfolioIdentifier, TransactionId transactionIdentifier,
                                        long amountToPayInCents) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountToPayInCents = amountToPayInCents;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public long getAmountToPayInCents() {
        return amountToPayInCents;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
