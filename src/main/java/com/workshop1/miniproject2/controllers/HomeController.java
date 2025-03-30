package com.workshop1.miniproject2.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button BusinessCollabMngBtn = new Button();
    @FXML
    private Button EmployeeMngBtn = new Button();
    @FXML
    public void openEmployeeMng(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/workshop1/miniproject2/views/employee-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Employee Management System");
        stage.setScene(scene);
        stage.show(); // creates and shows new stage

        Stage stage2 = (Stage) EmployeeMngBtn.getScene().getWindow(); //gets current stage
        stage2.close();

    }

    @FXML
    public void openProductMng(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/workshop1/miniproject2/views/product-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage() ;
        stage.setTitle("Product Management");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void openBuildingMng(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/workshop1/miniproject2/views/building-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage() ;
        stage.setTitle("Building Management");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void openRoomMng(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/workshop1/miniproject2/views/room-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage() ;
        stage.setTitle("Room Management");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void openBuildingCollabMng(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/workshop1/miniproject2/views/businessCollab-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage() ;
        stage.setTitle("Business Collab Management");
        stage.setScene(scene);
        stage.show();

        Stage stage2 = (Stage) BusinessCollabMngBtn.getScene().getWindow();
        stage2.close();
    }
}
