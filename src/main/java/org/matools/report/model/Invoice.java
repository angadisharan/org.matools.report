package org.matools.report.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Invoice {

    private final String invoiceNumber;
    private final LocalDate date;
    private final Party customer;
    private final Party seller;
    private final List<InvoiceItem> items;
    private final BigDecimal totalAmount;
    private final String currency;

    private Invoice(Builder builder) {
        this.invoiceNumber = builder.invoiceNumber;
        this.date = builder.date;
        this.customer = builder.customer;
        this.seller = builder.seller;
        this.items = builder.items;
        this.totalAmount = builder.totalAmount;
        this.currency = builder.currency;
    }

    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDate getDate() { return date; }
    public Party getCustomer() { return customer; }
    public Party getSeller() { return seller; }
    public List<InvoiceItem> getItems() { return items; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getCurrency() { return currency; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String invoiceNumber;
        private LocalDate date;
        private Party customer;
        private Party seller;
        private List<InvoiceItem> items = Collections.emptyList();
        private BigDecimal totalAmount = BigDecimal.ZERO;
        private String currency = "INR";

        public Builder invoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder customer(Party customer) {
            this.customer = customer;
            return this;
        }

        public Builder seller(Party seller) {
            this.seller = seller;
            return this;
        }

        public Builder items(List<InvoiceItem> items) {
            this.items = items;
            return this;
        }

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Invoice build() {
            validate();
            return new Invoice(this);
        }

        private void validate() {
            if (invoiceNumber == null || invoiceNumber.isEmpty()) {
                throw new IllegalArgumentException("Invoice number is required");
            }
            if (customer == null) {
                throw new IllegalArgumentException("Customer is required");
            }
        }
    }
}