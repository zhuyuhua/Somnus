package com.somnus.company.command;

import com.somnus.company.domain.vo.CompanyId;
import com.somnus.users.domain.vo.UserId;


public class CreateCompanyCommand {
	
	private CompanyId companyId;
    private UserId userId;
    private String companyName;
    private long companyValue;
    private long amountOfShares;

    public CreateCompanyCommand(CompanyId companyId, UserId userId, String companyName, long companyValue, long amountOfShares) {
        this.companyId = companyId;
        this.amountOfShares = amountOfShares;
        this.companyName = companyName;
        this.companyValue = companyValue;
        this.userId = userId;
    }

    public long getAmountOfShares() {
        return amountOfShares;
    }

    public String getCompanyName() {
        return companyName;
    }

    public long getCompanyValue() {
        return companyValue;
    }

    public UserId getUserId() {
        return userId;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

}
