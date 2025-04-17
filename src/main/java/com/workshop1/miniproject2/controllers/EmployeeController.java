package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.Employee;
import com.workshop1.miniproject2.models.EmployeeStore;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeController {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Employee, SimpleStringProperty> firstNameColumn;

    @FXML
    private TableColumn<Employee, SimpleStringProperty> lastNameColumn;

    @FXML
    private TableColumn<Employee,SimpleIntegerProperty> ageColumn;

    @FXML
    private TableColumn<Employee, SimpleDoubleProperty> salaryColumn;

    @FXML
    private TextField  firstNameFId;

    @FXML
    private TextField lastNameFId;

    @FXML
    private TextField ageFId;

    @FXML
    private TextField salaryFId;

    @FXML
    private Label errorMessage;

    @FXML
    private TableView<Employee> employeeTable;

    private final EmployeeStore employeeStore = new EmployeeStore();


    @FXML
    public void initialize() {
        System.out.println("Employee Controller Initialize");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        //assigns columns to corresponding properties

        ObservableList<Employee> employees = employeeStore.getEmployees();
        employeeTable.setItems(employees);
        //gets array of employees and puts them into the table

        employeeTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                firstNameFId.setText(selectedEmployee.getFirstName());
                lastNameFId.setText(selectedEmployee.getLastName());
                ageFId.setText(String.valueOf(selectedEmployee.getAge()));
                salaryFId.setText(String.valueOf(selectedEmployee.getSalary()));
            }
        });
        //selects employee that is clicked and puts its values into the corresponding text fields
    }

    //Adds employee to array with error validation
    @FXML
    void addEmployee(ActionEvent event) {
        System.out.println("Add Person");
        String error = "";
        boolean isValid = true;

        String firstName = firstNameFId.getText();
        if (firstName.isEmpty()) {
            error += "First Name cannot be empty!\n";
            isValid = false;
        }

        String lastName = lastNameFId.getText();
        if (lastName.isEmpty()) {
            error += "Last Name cannot be empty!\n";
            isValid = false;
        }

        Integer age = null;
        if (ageFId.getText().isEmpty()) {
            error += "Age cannot be empty!\n";
            isValid = false;
        } else try {
            age = Integer.parseInt(ageFId.getText());
        }catch (NumberFormatException e) {
            error += "Age must be an integer!\n";
            isValid = false;
        }

        Double salary = null;
        if (salaryFId.getText().isEmpty()) {
            error += "Salary cannot be empty!\n";
            isValid = false;
        } else try {
            salary = Double.parseDouble(salaryFId.getText());
        } catch (NumberFormatException e) {
            error += "Salary must be an number!\n";
            isValid = false;
        }

        if (isValid) {
            employeeStore.addEmployee(new Employee(firstName,lastName,age, salary));

            firstNameFId.clear();
            lastNameFId.clear();
            ageFId.clear();
            salaryFId.clear();
            errorMessage.setText("");
        } else {
            errorMessage.setText(error);
        }
    }

    //Updates selected employee with error validation
    @FXML
    void updateEmployee (ActionEvent event) {
        System.out.println("Update Employee");
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            String error = "";
            boolean isValid = true;

            String firstName = firstNameFId.getText();
            if (firstName.isEmpty()) {
                error += "First Name cannot be empty!\n";
                isValid = false;
            }

            String lastName = lastNameFId.getText();
            if (lastName.isEmpty()) {
                error += "Last Name cannot be empty!\n";
                isValid = false;
            }

            Integer age = null;
            if (ageFId.getText().isEmpty()) {
                error += "Age cannot be empty!\n";
                isValid = false;
            } else try {
                age = Integer.parseInt(ageFId.getText());
            }catch (NumberFormatException e) {
                error += "Age must be an integer!\n";
                isValid = false;
            }

            Double salary = null;
            if (salaryFId.getText().isEmpty()) {
                error += "Salary cannot be empty!\n";
                isValid = false;
            } else try {
                salary = Double.parseDouble(salaryFId.getText());
            } catch (NumberFormatException e) {
                error += "Salary must be an number!\n";
                isValid = false;
            }

            if (isValid) {
                employeeStore.updateEmployee(selectedEmployee, firstName, lastName, age, salary);
                selectedEmployee.setFirstName(firstName);
                selectedEmployee.setLastName(lastName);
                selectedEmployee.setAge(age);
                selectedEmployee.setSalary(salary);

                employeeTable.refresh();
                errorMessage.setText("");
            } else {
                errorMessage.setText(error);
            }
        }
    }

    //deletes selected employee as long as not null
    @FXML
    public void deleteEmployee (ActionEvent event) {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            employeeStore.deleteEmployee(selectedEmployee);
        }
    }

    //go backs to home stage
    @FXML
    public void backAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/workshop1/miniproject2/views/home-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Company Management System");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }




}
