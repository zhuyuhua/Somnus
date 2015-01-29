package com.somnus.company.domain.event;

import com.somnus.company.domain.vo.CompanyId;


public class CompanyCreatedEvent {
	
	private CompanyId companyId;
    private String companyName;
    private long companyValue;
    private long amountOfShares;

    public CompanyCreatedEvent(CompanyId companyId, String companyName, long amountOfShares, long companyValue) {
        this.amountOfShares = amountOfShares;
        this.companyName = companyName;
        this.companyValue = companyValue;
        this.companyId = companyId;
    }

    public CompanyId getCompanyIdentifier() {
        return this.companyId;
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

}
