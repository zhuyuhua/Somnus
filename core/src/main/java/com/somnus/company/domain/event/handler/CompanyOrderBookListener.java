package com.somnus.company.domain.event.handler;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.somnus.company.command.AddOrderBookToCompanyCommand;
import com.somnus.company.command.CreateOrderBookCommand;
import com.somnus.company.domain.event.CompanyCreatedEvent;
import com.somnus.orders.domain.vo.OrderBookId;

@Component
public class CompanyOrderBookListener {
	
	private final static Logger logger = LoggerFactory.getLogger(CompanyOrderBookListener.class);
    private CommandBus commandBus;

    @EventHandler
    public void handleCompanyCreated(CompanyCreatedEvent event) {
        logger.debug("About to dispatch a new command to create an OrderBook for the company {}",
                event.getCompanyIdentifier());

        OrderBookId orderBookId = new OrderBookId();
        CreateOrderBookCommand createOrderBookCommand = new CreateOrderBookCommand(orderBookId);
        commandBus.dispatch(new GenericCommandMessage<CreateOrderBookCommand>(createOrderBookCommand));

        AddOrderBookToCompanyCommand addOrderBookToCompanyCommand =
                new AddOrderBookToCompanyCommand(event.getCompanyIdentifier(), orderBookId);
        commandBus.dispatch(new GenericCommandMessage<AddOrderBookToCompanyCommand>(addOrderBookToCompanyCommand));
    }

    @Autowired
    public void setCommandBus(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

}
