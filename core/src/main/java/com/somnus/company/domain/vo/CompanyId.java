package com.somnus.company.domain.vo;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import com.somnus.core.ValueObject;

public class CompanyId implements ValueObject{

	private static final long serialVersionUID = -2716145147319770562L;

	private final String identifier;
    private final int hashCode;

    public CompanyId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
        this.hashCode = identifier.hashCode();
    }

    public CompanyId(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
        this.hashCode = identifier.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (o == null || getClass() != o.getClass()) {
			return false;
		}

        CompanyId companyId = (CompanyId) o;

        return identifier.equals(companyId.identifier);

    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return identifier;
    }
    
}
