package com.somnus.portfolio.domain.event.stock;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;
public class ItemReservationCancelledForPortfolioEvent {
    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    private TransactionId transactionIdentifier;
    private long amountOfCancelledItems;

    public ItemReservationCancelledForPortfolioEvent(PortfolioId portfolioIdentifier,
                                                     OrderBookId orderBookIdentifier,
                                                     TransactionId transactionIdentifier,
                                                     long amountOfCancelledItems) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfCancelledItems = amountOfCancelledItems;
    }

    public long getAmountOfCancelledItems() {
        return amountOfCancelledItems;
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
