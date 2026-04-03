package org.matools.report.model;

public class Discount {

    public enum Type {
        PERCENTAGE,
        FLAT
    }

    private Type type;
    private double value;

    public Discount(Type type, double value) {
        this.type = type;
        this.value = value;
    }

    public double apply(double amount) {
        if (type == Type.PERCENTAGE) {
            return amount * (value / 100);
        } else {
            return value;
        }
    }

    public Type getType() { return type; }
    public double getValue() { return value; }
}