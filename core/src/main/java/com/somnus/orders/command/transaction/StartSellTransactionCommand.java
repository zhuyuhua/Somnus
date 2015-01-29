package com.somnus.orders.command.transaction;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.PortfolioId;
import com.somnus.orders.domain.vo.TransactionId;

public class StartSellTransactionCommand extends AbstractStartTransactionCommand {

    public StartSellTransactionCommand(TransactionId transactionId, OrderBookId orderbookIdentifier, PortfolioId portfolioIdentifier, long tradeCount, long itemPrice) {
        super(transactionId, orderbookIdentifier, portfolioIdentifier, tradeCount, itemPrice);
    }
}
