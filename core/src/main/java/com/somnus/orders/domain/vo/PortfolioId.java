package com.somnus.orders.domain.vo;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import com.somnus.core.ValueObject;

public class PortfolioId implements ValueObject {

	private static final long serialVersionUID = 2306356568910768124L;
	
	private String identifier;

	    public PortfolioId() {
	        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
	    }

	    public PortfolioId(String identifier) {
	        Assert.notNull(identifier, "Identifier may not be null");
	        this.identifier = identifier;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) {
				return true;
			}
	        if (o == null || getClass() != o.getClass()) {
				return false;
			}

	        PortfolioId that = (PortfolioId) o;

	        return identifier.equals(that.identifier);

	    }

	    @Override
	    public int hashCode() {
	        return identifier.hashCode();
	    }

	    @Override
	    public String toString() {
	        return identifier;
	    }
}
