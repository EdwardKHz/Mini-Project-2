package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductController {

    @FXML
    private TextField typeField, quantityField, costField, valueField;
    @FXML
    private Button addButton, deleteButton, modifyButton;
    @FXML
    private TableView<Products> productTable;
    @FXML
    private TableColumn<Products, String> typeColumn;
    @FXML
    private TableColumn<Products, Integer> quantityColumn;
    @FXML
    private TableColumn<Products, Double> costColumn;
    @FXML
    private TableColumn<Products, Double> valueColumn;

    private ObservableList<Products> productList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());

        productTable.setItems(productList);

        addButton.setOnAction(event -> handleAddProduct());
        deleteButton.setOnAction(event -> handleDeleteProduct());
        modifyButton.setOnAction(event -> handleModifyProduct());


        quantityField.textProperty().addListener((observable, oldValue, newValue) -> updateValue());
        costField.textProperty().addListener((observable, oldValue, newValue) -> updateValue());
    }

    @FXML
    public void handleAddProduct() {
        try {
            String type = typeField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double cost = Double.parseDouble(costField.getText());
            double value = quantity * cost;

            if (!type.isEmpty() && quantity > 0 && cost > 0) {
                Products newProduct = new Products(type, quantity, cost, value);
                productList.add(newProduct);
                clearFields();
            } else {
                showAlert("Invalid Input", "Please ensure all fields are correctly filled.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for quantity and cost.");
        }
    }

    @FXML
    public void handleDeleteProduct() {
        Products selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productList.remove(selectedProduct);
        } else {
            showAlert("No Product Selected", "Please select a product to delete.");
        }
    }

    @FXML
    public void handleModifyProduct() {
        Products selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            typeField.setText(selectedProduct.getType());
            quantityField.setText(String.valueOf(selectedProduct.getQuantity()));
            costField.setText(String.valueOf(selectedProduct.getCost()));
            valueField.setText(String.valueOf(selectedProduct.getValue()));

            productList.remove(selectedProduct);
        } else {
            showAlert("No Product Selected", "Please select a product to modify.");
        }
    }

    private void updateValue() {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            double cost = Double.parseDouble(costField.getText());
            double value = quantity * cost;
            valueField.setText(String.valueOf(value));
        } catch (NumberFormatException e) {
            valueField.clear();
        }
    }

    private void clearFields() {
        typeField.clear();
        quantityField.clear();
        costField.clear();
        valueField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}