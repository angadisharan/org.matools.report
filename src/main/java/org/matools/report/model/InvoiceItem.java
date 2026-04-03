package org.matools.report.model;

public class InvoiceItem {

    private String name;
    private int quantity;
    private double price;

    public InvoiceItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public double getTotal() {
        return quantity * price;
    }
}