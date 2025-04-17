package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.Products;
import com.workshop1.miniproject2.models.ProductStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductController {

    @FXML private TextField typeField, quantityField, costField, valueField;
    @FXML private Button addButton, deleteButton, modifyButton, backBtn;
    @FXML private TableView<Products> productTable;
    @FXML private TableColumn<Products, String> typeColumn;
    @FXML private TableColumn<Products, Integer> quantityColumn;
    @FXML private TableColumn<Products, Double> costColumn;
    @FXML private TableColumn<Products, Double> valueColumn;

    private ObservableList<Products> productList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());

        // Load products from the database
        productList = ProductStore.getAllProducts();
        productTable.setItems(productList);

        addButton.setOnAction(event -> handleAddProduct());
        deleteButton.setOnAction(event -> handleDeleteProduct());
        modifyButton.setOnAction(event -> handleModifyProduct());

        quantityField.textProperty().addListener((observable, oldValue, newValue) -> updateValue());
        costField.textProperty().addListener((observable, oldValue, newValue) -> updateValue());
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

    @FXML
    public void handleAddProduct() {
        try {
            String type = typeField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double cost = Double.parseDouble(costField.getText());
            double value = quantity * cost;

            if (!type.isEmpty() && quantity > 0 && cost > 0) {
                Products newProduct = new Products(type, quantity, cost, value);
                ProductStore.addProduct(newProduct);  // Add to DB
                productList.add(newProduct);  // Update table view
                clearFields();
            } else {
                showAlert("Invalid Input", "Ensure all fields are correctly filled.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers");
        }
    }

    @FXML
    public void handleDeleteProduct() {
        Products selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            int productId = selectedProduct.getId();
            ProductStore.deleteProduct(productId);
            productList.remove(selectedProduct);
        } else {
            showAlert("No Product Selected", "Select a product to delete");
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
            selectedProduct.setType(typeField.getText());
            selectedProduct.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedProduct.setCost(Double.parseDouble(costField.getText()));
            selectedProduct.setValue(Double.parseDouble(valueField.getText()));

            ProductStore.updateProduct(selectedProduct, selectedProduct.getId());
            productList.add(selectedProduct);
        } else {
            showAlert("No Product Selected", "Select a product to modify");
        }
    }

    private void clearFields() {
        typeField.clear();
        quantityField.clear();
        costField.clear();
        valueField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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