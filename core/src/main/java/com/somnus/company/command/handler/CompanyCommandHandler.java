package com.somnus.company.command.handler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.somnus.company.command.AddOrderBookToCompanyCommand;
import com.somnus.company.command.CreateCompanyCommand;
import com.somnus.company.domain.aggregate.Company;

public class CompanyCommandHandler {

	private Repository<Company> repository;

    @CommandHandler
    public void handleCreateCompany(CreateCompanyCommand command) {
        Company company = new Company(command.getCompanyId(),
                command.getCompanyName(),
                command.getCompanyValue(),
                command.getAmountOfShares());
        repository.add(company);
    }

    @CommandHandler
    public void handleAddOrderBook(AddOrderBookToCompanyCommand command) {
        Company company = repository.load(command.getCompanyId());
        company.addOrderBook(command.getOrderBookId());
    }

    @Autowired
    @Qualifier("companyRepository")
    public void setRepository(Repository<Company> companyRepository) {
        this.repository = companyRepository;
    }
}
