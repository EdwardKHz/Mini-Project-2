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
        String username=user.getText();
        String password=pass.getText();

        if(username.isEmpty()||password.isEmpty()){
            errorMsg.setText("Please enter all the fields");
            errorMsg.setVisible(true);
        }
        else if((username.equals("Joe")&&password.equals("Ghorayeb"))||(username.equals("Edward")&&password.equals("Khazzoum"))||(username.equals("Georges")&&password.equals("Samia"))||(username.equals("Joseph")&&password.equals("Yazbeck"))||(username.equals("Joseph")&&password.equals("Chidiac"))){
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
        }
        else{
            errorMsg.setText("Incorrect username and/or password");
            errorMsg.setVisible(true);


        }
    }
}
