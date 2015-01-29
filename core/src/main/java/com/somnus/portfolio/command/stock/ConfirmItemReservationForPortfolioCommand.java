package com.somnus.portfolio.command.stock;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class ConfirmItemReservationForPortfolioCommand {

    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    private TransactionId transactionIdentifier;
    private long amountOfItemsToConfirm;

    public ConfirmItemReservationForPortfolioCommand(PortfolioId portfolioIdentifier,
                                                     OrderBookId orderBookIdentifier,
                                                     TransactionId transactionIdentifier,
                                                     long amountOfItemsToConfirm) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.transactionIdentifier = transactionIdentifier;
        this.amountOfItemsToConfirm = amountOfItemsToConfirm;
    }

    public long getAmountOfItemsToConfirm() {
        return amountOfItemsToConfirm;
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
