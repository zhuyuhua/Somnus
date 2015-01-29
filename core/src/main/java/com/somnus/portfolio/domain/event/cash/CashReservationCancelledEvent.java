package com.somnus.portfolio.domain.event.cash;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class CashReservationCancelledEvent {
    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    private long amountOfMoneyToCancel;

    public CashReservationCancelledEvent(PortfolioId portfolioIdentifier,
                                         TransactionId transactionIdentifier,
                                         long amountOfMoneyToCancel) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfMoneyToCancel = amountOfMoneyToCancel;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public long getAmountOfMoneyToCancel() {
        return amountOfMoneyToCancel;
    }
}
