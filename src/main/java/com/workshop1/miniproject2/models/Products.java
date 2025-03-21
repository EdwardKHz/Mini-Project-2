package com.workshop1.miniproject2.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {
    private SimpleStringProperty type;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty cost;
    private SimpleDoubleProperty value;

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

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public double getCost() {
        return cost.get();
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public double getValue() {
        return value.get();
    }

    public void setValue(double value) {
        this.value.set(value);
    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }
}