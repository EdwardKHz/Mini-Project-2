package com.workshop1.miniproject2.models;

public class Building {
    private String bmName;
    private String bmLocation;
    private String bmFloor;

    public Building(String bmName, String bmLocation, String bmFloor) {
        this.bmName = bmName;
        this.bmLocation = bmLocation;
        this.bmFloor = bmFloor;
    }

    public String getBmName() {
        return bmName;
    }

    public String getBmLocation() {
        return bmLocation;
    }

    public String getBmFloor() {
        return bmFloor;
    }
}
