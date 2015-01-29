package com.somnus.users.domain.vo;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import com.somnus.core.ValueObject;


public class UserId implements ValueObject {

	private static final long serialVersionUID = 56187494959056060L;

	    private String identifier;

	    public UserId() {
	        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
	    }

	    public UserId(String identifier) {
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

	        UserId userId = (UserId) o;

	        return identifier.equals(userId.identifier);

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
