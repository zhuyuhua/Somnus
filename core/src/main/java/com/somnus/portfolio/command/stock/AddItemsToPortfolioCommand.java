package com.somnus.portfolio.command.stock;

import javax.validation.constraints.Min;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;

/**
 * Try to add new items for a specific OrderBook to the portfolio.
 *
 * @author Jettro Coenradie
 */
public class AddItemsToPortfolioCommand {

    private PortfolioId portfolioIdentifier;
    private OrderBookId orderBookIdentifier;
    @Min(0)
    private long amountOfItemsToAdd;

    /**
     * Create a new command.
     *
     * @param portfolioIdentifier Identifier of the Portfolio to add items to
     * @param orderBookIdentifier Identifier of the OrderBook to add items for
     * @param amountOfItemsToAdd  AMount of items to add
     */
    public AddItemsToPortfolioCommand(PortfolioId portfolioIdentifier, OrderBookId orderBookIdentifier,
                                      long amountOfItemsToAdd) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
        this.amountOfItemsToAdd = amountOfItemsToAdd;
    }

    public long getAmountOfItemsToAdd() {
        return amountOfItemsToAdd;
    }

    public OrderBookId getOrderBookIdentifier() {
        return orderBookIdentifier;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    @Override
    public String toString() {
        return "AddItemsToPortfolioCommand{" +
                "amountOfItemsToAdd=" + amountOfItemsToAdd +
                ", portfolioIdentifier=" + portfolioIdentifier +
                ", orderBookIdentifier=" + orderBookIdentifier +
                '}';
    }
}
