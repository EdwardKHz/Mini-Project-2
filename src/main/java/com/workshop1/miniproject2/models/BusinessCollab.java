package com.workshop1.miniproject2.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BusinessCollab {
    private final SimpleStringProperty bName;
    private final SimpleStringProperty bLocation;
    private final SimpleStringProperty bType;

    public BusinessCollab(String bName, String bLocation, String bType) {
        this.bName = new SimpleStringProperty(bName);
        this.bLocation = new SimpleStringProperty(bLocation);
        this.bType = new SimpleStringProperty(bType);
    }
    public String getbName() {
        return bName.get();
    }
    public void setbName(String bName) {
        this.bName.set(bName);
    }
    public StringProperty bNameProperty(){
        return this.bName;
    }
    public String getbLocation() {
        return bLocation.get();
    }
    public void setbLocation(String bLocation) {
        this.bLocation.set(bLocation);
    }
    public StringProperty bLocationProperty(){
        return this.bLocation;
    }
    public String getbType() {
        return bType.get();
    }
    public void setbType(String bType) {
        this.bType.set(bType);
    }
    public StringProperty bTypeProperty(){
        return this.bType;
    }
}
