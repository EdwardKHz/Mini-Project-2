package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeStore {
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    public EmployeeStore() {
        this.employees.addAll(
                new Employee("John", "Smith", 24, 2300),
                new Employee("Perla", "Khazzoum", 19, 4300)
        );
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        if (employee != null) {
            employees.add(employee);
        }
    }
    public void deleteEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void updateEmployee(Employee employee, String firstName, String lastName, int age, double salary) {
        if (employee != null) {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setAge(age);
            employee.setSalary(salary);
        }
    }

}
