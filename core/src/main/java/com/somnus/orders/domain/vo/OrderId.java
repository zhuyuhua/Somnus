package com.somnus.orders.domain.vo;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import com.somnus.core.ValueObject;

public class OrderId implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2158181246329250494L;

	private String identifier;

    /**
     * Constructor uses the <code>IdentifierFactory</code> to generate an identifier.
     */
    public OrderId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public OrderId(String identifier) {
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

        OrderId orderId = (OrderId) o;

        return identifier.equals(orderId.identifier);

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
