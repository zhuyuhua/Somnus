package com.somnus.portfolio.command.stock;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

/**
 * Cancel a reservation for an amount of items for the OrderBook belonging to the provided identifier in the Portfolio
 * of the provided identifier.
 *
 * @author Jettro Coenradie
 */
public class CancelItemReservationForPortfolioCommand {

    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    private TransactionId transactionIdentifier;
    private long amountOfCancelledItems;

    public CancelItemReservationForPortfolioCommand(PortfolioId portfolioIdentifier,
                                                    OrderBookId orderBookIdentifier,
                                                    TransactionId transactionIdentifier,
                                                    long amountOfCancelledItems) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.transactionIdentifier = transactionIdentifier;

        this.amountOfCancelledItems = amountOfCancelledItems;
    }

    public long getAmountOfItemsToCancel() {
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
