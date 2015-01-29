package com.somnus.company.command;

import com.somnus.company.domain.vo.CompanyId;
import com.somnus.orders.domain.vo.OrderBookId;

public class AddOrderBookToCompanyCommand {
	
	private CompanyId companyId;
    private OrderBookId orderBookId;

    public AddOrderBookToCompanyCommand(CompanyId companyId, OrderBookId orderBookId) {
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
