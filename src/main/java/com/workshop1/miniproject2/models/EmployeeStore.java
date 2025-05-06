package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;


public class EmployeeStore {


    ObservableList<Employee> employees = FXCollections.observableArrayList();
    String dbURL = "jdbc:mysql://localhost:3306/workshopdb";
    String dbUsername = "root";
    String dbPassword = "edward1234";

    public EmployeeStore() {

    }


    public ObservableList<Employee> getEmployees() {
        String query = "SELECT * FROM employees";
        employees.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getDouble("salary")
                );
                employees.add(employee);
            }
            connection.close();
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

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setDouble(4, employee.getSalary());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                employee.setId(keys.getInt(1));
            }

            employees.add(employee);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(Employee employee) {
        String query = "DELETE FROM employees WHERE id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.executeUpdate();

            employees.remove(employee);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee, String firstName, String lastName, int age, double salary) {
        String query = "UPDATE employees SET firstName = ?, lastName = ?, age = ?, salary = ? WHERE id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.setDouble(4, salary);
            statement.setInt(5, employee.getId());

            statement.executeUpdate();

            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setAge(age);
            employee.setSalary(salary);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
