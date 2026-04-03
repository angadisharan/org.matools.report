package org.matools.report.model;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

    private String invoiceNumber;
    private LocalDate date;
    private String customerName;
    private String customerEmail;
    private List<InvoiceItem> items;
    private double totalAmount;

    private Invoice() {}

    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDate getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public List<InvoiceItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Invoice invoice = new Invoice();

        public Builder invoiceNumber(String invoiceNumber) {
            invoice.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder date(LocalDate date) {
            invoice.date = date;
            return this;
        }

        public Builder customerName(String name) {
            invoice.customerName = name;
            return this;
        }

        public Builder customerEmail(String email) {
            invoice.customerEmail = email;
            return this;
        }

        public Builder items(List<InvoiceItem> items) {
            invoice.items = items;
            return this;
        }

        public Builder totalAmount(double total) {
            invoice.totalAmount = total;
            return this;
        }

        public Invoice build() {
            return invoice;
        }
    }
}