/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.somnus.orders.domain.event;

import java.io.Serializable;

import com.somnus.orders.domain.vo.OrderBookId;
import com.somnus.orders.domain.vo.OrderId;
import com.somnus.orders.domain.vo.TransactionId;

public class TradeExecutedEvent implements Serializable {
    private static final long serialVersionUID = 6292249351659536792L;

    private final long tradeCount;
    private final long tradePrice;
    private final OrderId buyOrderId;
    private final OrderId sellOrderId;
    private final TransactionId buyTransactionId;
    private final TransactionId sellTransactionId;
    private final OrderBookId orderBookId;

    public TradeExecutedEvent(OrderBookId orderBookId,
                              long tradeCount,
                              long tradePrice,
                              OrderId buyOrderId,
                              OrderId sellOrderId,
                              TransactionId buyTransactionId,
                              TransactionId sellTransactionId) {
        this.tradeCount = tradeCount;
        this.tradePrice = tradePrice;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.sellTransactionId = sellTransactionId;
        this.buyTransactionId = buyTransactionId;
        this.orderBookId = orderBookId;
    }

    public OrderBookId getOrderBookIdentifier() {
        return this.orderBookId;
    }

    public long getTradeCount() {
        return tradeCount;
    }

    public long getTradePrice() {
        return tradePrice;
    }

    public OrderId getBuyOrderId() {
        return buyOrderId;
    }

    public OrderId getSellOrderId() {
        return sellOrderId;
    }

    public TransactionId getBuyTransactionId() {
        return buyTransactionId;
    }

    public TransactionId getSellTransactionId() {
        return sellTransactionId;
    }
}
