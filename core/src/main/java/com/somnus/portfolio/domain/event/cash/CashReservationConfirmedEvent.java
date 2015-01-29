package com.somnus.portfolio.domain.event.cash;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class CashReservationConfirmedEvent {
    private TransactionId transactionIdentifier;
    private PortfolioId portfolioIdentifier;
    private long amountOfMoneyConfirmedInCents;

    public CashReservationConfirmedEvent(PortfolioId portfolioIdentifier,
                                         TransactionId transactionId,
                                         long amountOfMoneyConfirmedInCents) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionId;
        this.amountOfMoneyConfirmedInCents = amountOfMoneyConfirmedInCents;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public long getAmountOfMoneyConfirmedInCents() {
        return amountOfMoneyConfirmedInCents;
    }
}
