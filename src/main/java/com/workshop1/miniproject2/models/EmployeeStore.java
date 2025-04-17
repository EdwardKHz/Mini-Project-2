package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;


public class EmployeeStore {


    ObservableList<Employee> employees = FXCollections.observableArrayList();
    String dbURL = "jdbc:mysql://localhost:3306/workshopdb";
    String dbUsername = "root";
    String dbPassword = "workshop2025";

    public EmployeeStore() {

    }


    public ObservableList<Employee> getEmployees() {
        String query = "SELECT * FROM employees";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    Employee employee = new Employee(
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getDouble("salary")
                    );
                    employees.add(employee);
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employees (firstName, lastName, age, salary) VALUES (?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

             PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setInt(3, employee.getAge());
                statement.setDouble(4, employee.getSalary());
                statement.executeUpdate();
                refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployee(Employee employee) {
        String query = "DELETE FROM employees WHERE firstName = ? AND lastName = ? AND age = ? AND salary = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

             PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setDouble(4, employee.getSalary());
            statement.executeUpdate();
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee, String firstName, String lastName, int age, double salary) {
        String query = "UPDATE employees SET firstName = ?, lastName = ?, age = ?, salary = ? WHERE firstName = ? AND lastName = ? AND age = ? AND salary = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

             PreparedStatement statement = conn.prepareStatement(query);

           statement.setString(1, employee.getFirstName());
           statement.setString(2, employee.getLastName());
           statement.setInt(3, employee.getAge());
           statement.setDouble(4, employee.getSalary());
           statement.setString(5, firstName);
           statement.setString(6, lastName);
           statement.setInt(7, age);
           statement.setDouble(8, salary);
           statement.executeUpdate();
           refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() {
        String query = "SELECT * FROM employees";
        ObservableList<Employee> refresnedList = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getDouble("salary")
                );
                refresnedList.add(employee);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        employees.setAll(refresnedList);
    }



}
