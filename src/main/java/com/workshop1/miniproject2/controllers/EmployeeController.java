package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.Employee;
import com.workshop1.miniproject2.models.EmployeeStore;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class EmployeeController {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<Employee, SimpleStringProperty> firstNameColumn;

    @FXML
    private TableColumn<Employee, SimpleStringProperty> lastNameColumn;

    @FXML
    private TableColumn<Employee,SimpleIntegerProperty> ageColumn;

    @FXML
    private TableColumn<Employee, SimpleStringProperty> addressColumn;

    @FXML
    private TableColumn<Employee, SimpleDoubleProperty> salaryColumn;

    @FXML
    private TextField  firstNameFId;

    @FXML
    private TextField lastNameFId;

    @FXML
    private TextField ageFId;

    @FXML
    private TextField addressFId;

    @FXML
    private TextField salaryFId;

    @FXML
    private Text errorMessage;

    @FXML
    private TableView<Employee> employeeTable;

    private final EmployeeStore employeeStore = new EmployeeStore();

    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));

        ObservableList<Employee> employees = employeeStore.getEmployees();
        employeeTable.setItems(employees);

        employeeTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                firstNameFId.setText(selectedEmployee.getFirstName());
                lastNameFId.setText(selectedEmployee.getLastName());
                ageFId.setText(String.valueOf(selectedEmployee.getAge()));
                addressFId.setText(selectedEmployee.getAddress());
                salaryFId.setText(String.valueOf(selectedEmployee.getSalary()));
            }
        });
    }

    @FXML
    void addPerson(ActionEvent event) {
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
        String address = addressFId.getText();
        if (address.isEmpty()) {
            error += "Address cannot be empty!\n";
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
            employeeStore.addEmployee(new Employee(firstName,lastName,age,address,salary));

            firstNameFId.clear();
            lastNameFId.clear();
            ageFId.clear();
            addressFId.clear();
            salaryFId.clear();
            errorMessage.setText("");
        } else {
            errorMessage.setText(error);
        }
    }

    @FXML
    void updateEmployee (ActionEvent event) {
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
            String address = addressFId.getText();
            if (address.isEmpty()) {
                error += "Address cannot be empty!\n";
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
                employeeStore.updateEmployee(selectedEmployee, firstName, lastName, age, address, salary);
                errorMessage.setText("");
            } else {
                errorMessage.setText(error);
            }
        }
    }




}
