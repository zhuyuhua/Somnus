package com.somnus.orders.domain.event.transaction;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class BuyTransactionStartedEvent extends AbstractTransactionStartedEvent {

    public BuyTransactionStartedEvent(TransactionId transactionIdentifier,
                                      OrderBookId orderBookIdentifier,
                                      PortfolioId portfolioIdentifier,
                                      long totalItems,
                                      long pricePerItem) {
        super(transactionIdentifier, orderBookIdentifier, portfolioIdentifier, totalItems, pricePerItem);
    }
}
