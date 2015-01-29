package com.somnus.portfolio.domain.event.stock;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;

public class ItemsAddedToPortfolioEvent {
    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    private long amountOfItemsAdded;

    public ItemsAddedToPortfolioEvent(PortfolioId portfolioIdentifier, OrderBookId orderBookIdentifier, long amountOfItemsAdded) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.amountOfItemsAdded = amountOfItemsAdded;
    }

    public long getAmountOfItemsAdded() {
        return amountOfItemsAdded;
    }

    public OrderBookId getOrderBookIdentifier() {
        return orderBookIdentifier;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }
}
