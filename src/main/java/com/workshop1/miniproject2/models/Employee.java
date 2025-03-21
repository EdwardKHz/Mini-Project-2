package com.workshop1.miniproject2.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private final SimpleStringProperty firstName, lastName;
    private final SimpleIntegerProperty age;
    private final SimpleStringProperty address;
    private final SimpleDoubleProperty salary;

    public Employee(String firstName, String lastName, int age, String address, double salary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
        this.address = new SimpleStringProperty(address);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public int getAge() {
        return age.get();
    }
    public void setAge(int age) {
        this.age.set(age);
    }
    public String getAddress() {
        return address.get();
    }
    public void setAddress(String address) {
        this.address.set(address);
    }
    public double getSalary() {
        return salary.get();
    }
    public void setSalary(double salary) {
        this.salary.set(salary);
    }
}
