package com.workshop1.miniproject2.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LogInController {
    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Button logInBtn;

    @FXML
    private Label errorMsg;

    @FXML
    void logInAction(ActionEvent event) throws IOException {
        String username = user.getText();
        String password = pass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMsg.setText("Please enter all the fields");
            errorMsg.setVisible(true);
        } else if (validateCredentials(username, password)) {
            errorMsg.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/workshop1/miniproject2/views/home-view.fxml"));
            Parent homeRoot = loader.load();
            HomeController homeController = loader.getController();
            homeController.setWelcomeMsg(username);
            Scene homeScene = new Scene(homeRoot);
            Stage stage = (Stage) user.getScene().getWindow();
            stage.setScene(homeScene);
            stage.setTitle("Home - Company Management System");
            stage.show();
        } else {
            errorMsg.setText("Incorrect username and/or password");
            errorMsg.setVisible(true);
        }
    }


    private boolean validateCredentials(String username, String password) {
        String dbURL = "jdbc:mysql://localhost:3306/workshopdb";
        String dbUsername = "root";
        String dbPassword = "edward1234";

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try { Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement statement = conn.prepareStatement(query);

             statement.setString(1, username);
             statement.setString(2, password);

             ResultSet rs = statement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
