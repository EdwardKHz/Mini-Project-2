package com.workshop1.miniproject2.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void logInAction(ActionEvent event) {
        String username=user.getText();
        String password=pass.getText();

        if(username.isEmpty()||password.isEmpty()){
            errorMsg.setText("Please enter all the fields");
            errorMsg.setVisible(true);
        }
        else if(!username.equals("admin")||!password.equals("CSC265")){
            errorMsg.setText("Incorrect username and/or password");
            errorMsg.setVisible(true);
        }
        else{
            errorMsg.setVisible(false);

        }
    }
}
