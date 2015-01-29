package com.somnus.portfolio.command.cash;

import javax.validation.constraints.Min;

import com.somnus.orders.domain.vo.PortfolioId;

public class WithdrawCashCommand {

    private PortfolioId portfolioIdentifier;
    @Min(0)
    private long amountToPayInCents;

    public WithdrawCashCommand(PortfolioId portfolioIdentifier, long amountToPayInCents) {

        this.portfolioIdentifier = portfolioIdentifier;
        this.amountToPayInCents = amountToPayInCents;
    }

    public long getAmountToPayInCents() {
        return amountToPayInCents;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }
}
