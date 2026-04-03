package org.matools.report.model;

import java.math.BigDecimal;

public class Tax {

    private final String name;        // "GST", "VAT", "Sales Tax"
    private final BigDecimal rate;    // % (e.g., 18)
    private final BigDecimal amount;  // calculated value

    public Tax(String name, BigDecimal rate, BigDecimal amount) {
        this.name = name;
        this.rate = rate;
        this.amount = amount;
    }

    public String getName() { return name; }
    public BigDecimal getRate() { return rate; }
    public BigDecimal getAmount() { return amount; }
}