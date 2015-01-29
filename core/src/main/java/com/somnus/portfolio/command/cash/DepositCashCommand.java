package com.somnus.portfolio.command.cash;

import javax.validation.constraints.Min;

import com.somnus.orders.domain.vo.PortfolioId;

public class DepositCashCommand {

    private PortfolioId portfolioIdentifier;
    @Min(0)
    private long moneyToAddInCents;

    public DepositCashCommand(PortfolioId portfolioIdentifier, long moneyToAddInCents) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.moneyToAddInCents = moneyToAddInCents;
    }

    public long getMoneyToAddInCents() {
        return moneyToAddInCents;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }
}
