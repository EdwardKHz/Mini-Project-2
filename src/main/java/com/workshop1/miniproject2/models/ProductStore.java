package com.workshop1.miniproject2.models;

import com.workshop1.miniproject2.models.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProductStore {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/productdb";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword";

    public static ObservableList<Products> getAllProducts() {
        ObservableList<Products> products = FXCollections.observableArrayList();
        String query = "SELECT * FROM products";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Products product = new Products(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("quantity"),
                        rs.getDouble("cost"),
                        rs.getDouble("value")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static void addProduct(Products product) {
        String query = "INSERT INTO products (type, quantity, cost, value) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getType());
            stmt.setInt(2, product.getQuantity());
            stmt.setDouble(3, product.getCost());
            stmt.setDouble(4, product.getValue());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(Products product, int productId) {
        String query = "UPDATE products SET type = ?, quantity = ?, cost = ?, value = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getType());
            stmt.setInt(2, product.getQuantity());
            stmt.setDouble(3, product.getCost());
            stmt.setDouble(4, product.getValue());
            stmt.setInt(5, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}