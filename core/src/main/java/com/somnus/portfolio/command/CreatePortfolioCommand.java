package com.somnus.portfolio.command;

import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.users.domain.vo.UserId;

public class CreatePortfolioCommand {

    private PortfolioId portfolioId;
    private UserId userId;

    public CreatePortfolioCommand(PortfolioId portfolioId, UserId userId) {
        this.portfolioId = portfolioId;
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public PortfolioId getPortfolioId() {
        return portfolioId;
    }
}
