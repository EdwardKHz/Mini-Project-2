package com.workshop1.miniproject2.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
    private final SimpleIntegerProperty rNumber;
    private final SimpleIntegerProperty floor;
    private final SimpleStringProperty building;

    public Room(int rNumber, int floor, String building) {
        this.rNumber = new SimpleIntegerProperty(rNumber);
        this.floor = new SimpleIntegerProperty(floor);
        this.building = new SimpleStringProperty(building);
    }

    public int getrNumber() {
        return rNumber.get();
    }

    public void setrNumber(int rNumber) {
        this.rNumber.set(rNumber);
    }

    public IntegerProperty rNumberProperty() {
        return this.rNumber;
    }

    public int getFloor() {
        return floor.get();
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public IntegerProperty floorProperty() {
        return this.floor;
    }

    public String getBuilding() {
        return building.get();
    }

    public void setBuilding(String building) {
        this.building.set(building);
    }

    public StringProperty buildingProperty() {
        return this.building;
    }
}