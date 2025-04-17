package com.workshop1.miniproject2.models;

import javafx.beans.property.*;

public class Products {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty cost = new SimpleDoubleProperty();
    private final DoubleProperty value = new SimpleDoubleProperty();

    public Products(int id, String type, int quantity, double cost, double value) {
        this.id.set(id);
        this.type.set(type);
        this.quantity.set(quantity);
        this.cost.set(cost);
        this.value.set(value);
    }

    public Products(String type, int quantity, double cost, double value) {
        this.type.set(type);
        this.quantity.set(quantity);
        this.cost.set(cost);
        this.value.set(value);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
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