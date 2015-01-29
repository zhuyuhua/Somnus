package com.somnus.company.command;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import com.somnus.company.command.handler.CompanyCommandHandler;
import com.somnus.company.domain.aggregate.Company;
import com.somnus.company.domain.event.CompanyCreatedEvent;
import com.somnus.company.domain.vo.CompanyId;
import com.somnus.users.domain.vo.UserId;

public class CompanyCommandHandlerTest {

    private FixtureConfiguration fixture;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Company.class);
        CompanyCommandHandler commandHandler = new CompanyCommandHandler();
        commandHandler.setRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void testCreateCompany() {
        CompanyId aggregateIdentifier = new CompanyId();
        UserId userId = new UserId();
        CreateCompanyCommand command = new CreateCompanyCommand(aggregateIdentifier, userId, "TestItem", 1000, 10000);

        fixture.given()
                .when(command)
                .expectEvents(new CompanyCreatedEvent(aggregateIdentifier, "TestItem", 1000, 10000));
    }
}
