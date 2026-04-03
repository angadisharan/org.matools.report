package org.matools.report.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class InvoiceItem {

    private final String name;

    private final BigDecimal quantity;

    // Price before tax (optional if total provided)
    private final BigDecimal unitPrice;

    // Price including tax (optional)
    private final BigDecimal unitPriceWithTax;

    // Optional tax list
    private final List<Tax> taxes;

    // Optional final total override
    private final BigDecimal total;

    private InvoiceItem(Builder builder) {
        this.name = builder.name;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.unitPriceWithTax = builder.unitPriceWithTax;
        this.taxes = builder.taxes;
        this.total = builder.total;
    }

    public String getName() { return name; }

    public BigDecimal getQuantity() { return quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }

    public BigDecimal getUnitPriceWithTax() { return unitPriceWithTax; }

    public List<Tax> getTaxes() { return taxes; }

    /**
     * Subtotal before tax
     */
    public BigDecimal getSubtotal() {
        if (unitPrice != null) {
            return unitPrice.multiply(quantity);
        }
        return BigDecimal.ZERO;
    }

    public boolean hasTax() {
        return taxes != null && !taxes.isEmpty();
    }

    /**
     * Total tax amount for this item
     */
    public BigDecimal getTaxTotal() {
        if (!hasTax()) {
            return BigDecimal.ZERO;
        }

        return taxes.stream()
                .map(Tax::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    /**
     * Final total (priority-based calculation)
     */
    public BigDecimal getTotal() {

        // 1️⃣ If user provided total → use it
        if (total != null) {
            return total;
        }

        // 2️⃣ If price includes tax
        if (unitPriceWithTax != null) {
            return unitPriceWithTax.multiply(quantity);
        }

        // 3️⃣ Default: subtotal + tax
        return getSubtotal().add(getTaxTotal());
    }

    // -------- Builder --------

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .name(this.name)
                .quantity(this.quantity)
                .unitPrice(this.unitPrice)
                .unitPriceWithTax(this.unitPriceWithTax)
                .taxes(this.taxes)
                .total(this.total);
    }

    public static class Builder {

        private String name;
        private BigDecimal quantity = BigDecimal.ONE;

        private BigDecimal unitPrice;
        private BigDecimal unitPriceWithTax;

        private List<Tax> taxes = Collections.emptyList();

        private BigDecimal total;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder quantity(double quantity) {
            this.quantity = BigDecimal.valueOf(quantity);
            return this;
        }

        public Builder quantity(BigDecimal quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unitPrice(double price) {
            this.unitPrice = BigDecimal.valueOf(price);
            return this;
        }

        public Builder unitPrice(BigDecimal price) {
            this.unitPrice = price;
            return this;
        }

        public Builder unitPriceWithTax(double priceWithTax) {
            this.unitPriceWithTax = BigDecimal.valueOf(priceWithTax);
            return this;
        }

        public Builder unitPriceWithTax(BigDecimal priceWithTax) {
            this.unitPriceWithTax = priceWithTax;
            return this;
        }

        public Builder taxes(List<Tax> taxes) {
            this.taxes = taxes != null ? taxes : Collections.emptyList();
            return this;
        }

        public Builder total(double total) {
            this.total = BigDecimal.valueOf(total);
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public InvoiceItem build() {
            validate();
            return new InvoiceItem(this);
        }

        private void validate() {

            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Item name is required");
            }

            if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Quantity must be > 0");
            }

            if (unitPrice == null && unitPriceWithTax == null && total == null) {
                throw new IllegalArgumentException(
                        "Provide at least one of unitPrice, unitPriceWithTax, or total"
                );
            }
        }
    }
}