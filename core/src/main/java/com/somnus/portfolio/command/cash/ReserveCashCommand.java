package com.somnus.portfolio.command.cash;

import javax.validation.constraints.Min;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class ReserveCashCommand {

    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    @Min(0)
    private long amountOfMoneyToReserve;

    public ReserveCashCommand(PortfolioId portfolioIdentifier,
                              TransactionId transactionIdentifier,
                              long amountOfMoneyToReserve) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfMoneyToReserve = amountOfMoneyToReserve;
    }

    public long getAmountOfMoneyToReserve() {
        return amountOfMoneyToReserve;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
