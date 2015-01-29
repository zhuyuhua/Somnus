package com.somnus.portfolio.domain.event.cash;

import com.somnus.orders.domain.vo.PortfolioId;

public class CashDepositedEvent {
    private PortfolioId portfolioId;
    private long moneyAddedInCents;

    public CashDepositedEvent(PortfolioId portfolioId, long moneyAddedInCents) {
        this.portfolioId = portfolioId;
        this.moneyAddedInCents = moneyAddedInCents;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioId;
    }

    public long getMoneyAddedInCents() {
        return moneyAddedInCents;
    }
}
