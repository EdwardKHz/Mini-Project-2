package com.workshop1.miniproject2.models;

import javafx.beans.property.*;

public class Products {
    private final StringProperty type;
    private final IntegerProperty quantity;
    private final DoubleProperty cost;
    private final DoubleProperty value;

    public Products(String type, int quantity, double cost, double value) {
        this.type = new SimpleStringProperty(type);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.cost = new SimpleDoubleProperty(cost);
        this.value = new SimpleDoubleProperty(value);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public double getCost() {
        return cost.get();
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public DoubleProperty costProperty() {
        return cost;
    }

    public double getValue() {
        return value.get();
    }

    public void setValue(double value) {
        this.value.set(value);
    }

    public DoubleProperty valueProperty() {
        return value;
    }
}