package com.somnus.company.domain.aggregate;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.somnus.company.domain.event.CompanyCreatedEvent;
import com.somnus.company.domain.event.OrderBookAddedToCompanyEvent;
import com.somnus.company.domain.vo.CompanyId;
import com.somnus.orders.domain.vo.OrderBookId;

public class Company extends AbstractAnnotatedAggregateRoot<CompanyId> {
	private static final long serialVersionUID = 1281333590485564190L;

	@AggregateIdentifier
	private CompanyId companyId;

	protected Company() {
	}

	public Company(CompanyId companyId, String name, long value,
			long amountOfShares) {
		apply(new CompanyCreatedEvent(companyId, name, value, amountOfShares));
	}

	public void addOrderBook(OrderBookId orderBookId) {
		apply(new OrderBookAddedToCompanyEvent(companyId, orderBookId));
	}

	@Override
	public CompanyId getIdentifier() {
		return this.companyId;
	}

	@EventHandler
	public void handle(CompanyCreatedEvent event) {
		this.companyId = event.getCompanyIdentifier();
	}

}
