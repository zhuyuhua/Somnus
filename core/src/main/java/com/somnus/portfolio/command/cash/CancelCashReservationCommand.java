package com.somnus.portfolio.command.cash;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class CancelCashReservationCommand {

    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    private long amountOfMoneyToCancel;

    public CancelCashReservationCommand(PortfolioId portfolioIdentifier,
                                        TransactionId transactionIdentifier,
                                        long amountOfMoneyToCancel) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfMoneyToCancel = amountOfMoneyToCancel;
    }

    public long getAmountOfMoneyToCancel() {
        return amountOfMoneyToCancel;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
