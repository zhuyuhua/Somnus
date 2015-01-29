package com.somnus.company.domain.event;

import com.somnus.company.domain.vo.CompanyId;
import com.somnus.orders.domain.vo.OrderBookId;

public class OrderBookAddedToCompanyEvent {
	
	private CompanyId companyId;
    private OrderBookId orderBookId;

    public OrderBookAddedToCompanyEvent(CompanyId companyId, OrderBookId orderBookId) {
        this.companyId = companyId;
        this.orderBookId = orderBookId;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public OrderBookId getOrderBookId() {
        return orderBookId;
    }

}
