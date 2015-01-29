package com.somnus.orders.domain.vo;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import com.somnus.core.ValueObject;

public class OrderBookId implements ValueObject {
	
	private static final long serialVersionUID = 6002149343747288869L;
	
	private String identifier;

    public OrderBookId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public OrderBookId(String identifier) {
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

        OrderBookId that = (OrderBookId) o;

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
