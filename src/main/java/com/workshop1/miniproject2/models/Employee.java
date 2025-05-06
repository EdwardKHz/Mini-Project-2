package com.workshop1.miniproject2.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private int id;
    private final SimpleStringProperty firstName, lastName;
    private final SimpleIntegerProperty age;
    private final SimpleDoubleProperty salary;


    public Employee(int id,String firstName, String lastName, int age, double salary) {
        this.id = id;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public Employee(String firstName, String lastName, int age, double salary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public double getSalary() {
        return salary.get();
    }
    public void setSalary(double salary) {
        this.salary.set(salary);
    }
}
