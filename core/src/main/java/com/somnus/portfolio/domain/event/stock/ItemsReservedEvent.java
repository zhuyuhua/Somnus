
package com.somnus.portfolio.domain.event.stock;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class ItemsReservedEvent {
    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    private TransactionId transactionIdentifier;
    private long amountOfItemsReserved;

    public ItemsReservedEvent(PortfolioId portfolioIdentifier,
                              OrderBookId orderBookIdentifier,
                              TransactionId transactionIdentifier,
                              long amountOfItemsReserved) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfItemsReserved = amountOfItemsReserved;
    }

    public long getAmountOfItemsReserved() {
        return amountOfItemsReserved;
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
