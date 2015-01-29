package com.somnus.portfolio.domain.event;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.users.domain.vo.UserId;

public class PortfolioCreatedEvent {

    private PortfolioId portfolioId;
    private UserId userId;

    public PortfolioCreatedEvent(PortfolioId portfolioId, UserId userId) {
        this.portfolioId = portfolioId;
        this.userId = userId;
    }

    public UserId getUserId() {
        return this.userId;
    }

    public PortfolioId getPortfolioId() {
        return this.portfolioId;
    }
}
