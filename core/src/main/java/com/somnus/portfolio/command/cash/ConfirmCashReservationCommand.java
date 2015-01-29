package com.somnus.portfolio.command.cash;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class ConfirmCashReservationCommand {

    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    private long amountOfMoneyToConfirmInCents;


    public ConfirmCashReservationCommand(PortfolioId portfolioIdentifier,
                                         TransactionId transactionIdentifier,
                                         long amountOfMoneyToConfirmInCents) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfMoneyToConfirmInCents = amountOfMoneyToConfirmInCents;
    }

    public long getAmountOfMoneyToConfirmInCents() {
        return amountOfMoneyToConfirmInCents;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }

    @Override
    public String toString() {
        return "ConfirmCashReservationCommand{" +
                "amountOfMoneyToConfirmInCents=" + amountOfMoneyToConfirmInCents +
                ", portfolioIdentifier=" + portfolioIdentifier +
                ", transactionIdentifier=" + transactionIdentifier +
                '}';
    }
}
