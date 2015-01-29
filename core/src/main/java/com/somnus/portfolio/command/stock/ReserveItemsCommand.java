package com.somnus.portfolio.command.stock;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;


public class ReserveItemsCommand {

    private PortfolioId portfolioIdentifier;
    private TransactionId transactionIdentifier;
    private OrderBookId orderBookIdentifier;
    private long amountOfItemsToReserve;

    public ReserveItemsCommand(PortfolioId portfolioIdentifier,
                               OrderBookId orderBookIdentifier,
                               TransactionId transactionIdentifier,
                               long amountOfItemsToReserve) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfItemsToReserve = amountOfItemsToReserve;
        this.orderBookIdentifier = orderBookIdentifier;
    }

    public long getAmountOfItemsToReserve() {
        return amountOfItemsToReserve;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public OrderBookId getOrderBookIdentifier() {
        return orderBookIdentifier;
    }

    public TransactionId getTransactionIdentifier() {
        return transactionIdentifier;
    }
}
