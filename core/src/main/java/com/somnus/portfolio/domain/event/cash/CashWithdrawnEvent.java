
package com.somnus.portfolio.domain.event.cash;

import com.somnus.orders.domain.vo.PortfolioId;

public class CashWithdrawnEvent {
    private PortfolioId portfolioIdentifier;
    private long amountPaidInCents;

    public CashWithdrawnEvent(PortfolioId portfolioIdentifier, long amountPaidInCents) {
        this.portfolioIdentifier = portfolioIdentifier;
        this.amountPaidInCents = amountPaidInCents;
    }

    public PortfolioId getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public long getAmountPaidInCents() {
        return amountPaidInCents;
    }
}
