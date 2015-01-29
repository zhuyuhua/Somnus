package com.somnus.portfolio;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class NotEnoughItemsAvailableToReserveInPortfolio {
    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    private TransactionId transactionIdentifier;
    private long availableAmountOfItems;
    private long amountOfItemsToReserve;

    public NotEnoughItemsAvailableToReserveInPortfolio(PortfolioId portfolioIdentifier,
                                                       OrderBookId orderBookIdentifier,
                                                       TransactionId transactionIdentifier,
                                                       long availableAmountOfItems,
                                                       long amountOfItemsToReserve) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.availableAmountOfItems = availableAmountOfItems;
        this.amountOfItemsToReserve = amountOfItemsToReserve;
    }

    public long getAmountOfItemsToReserve() {
        return amountOfItemsToReserve;
    }

    public long getAvailableAmountOfItems() {
        return availableAmountOfItems;
    }

    public OrderBookId getOrderBookIdentifier() {
        return orderBookIdentifier;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
