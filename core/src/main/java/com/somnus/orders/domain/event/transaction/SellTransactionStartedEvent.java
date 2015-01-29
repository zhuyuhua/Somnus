package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class SellTransactionStartedEvent extends AbstractTransactionStartedEvent {

    public SellTransactionStartedEvent(TransactionId transactionIdentifier,
                                       OrderBookId orderbookIdentifier,
                                       PortfolioId portfolioIdentifier,
                                       long totalItems,
                                       long pricePerItem) {
        super(transactionIdentifier, orderbookIdentifier, portfolioIdentifier, totalItems, pricePerItem);
    }
}
