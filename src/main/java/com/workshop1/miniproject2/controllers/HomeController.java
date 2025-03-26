package com.workshop1.miniproject2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public void openEmployeeMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("/com/workshop1/miniproject2/views/employee-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage() ;
        stage.setTitle("Employee Management");
        stage.setScene(scene);
        stage.show();
    }


    public void openProductMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("com/workshop1/miniproject2/views/product-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage() ;
        stage.setTitle("Product Management");
        stage.setScene(scene);
        stage.show();
    }

    public void openBuildingMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("com/workshop1/miniproject2/views/building-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage() ;
        stage.setTitle("Building Management");
        stage.setScene(scene);
        stage.show();
    }

    public void openRoomMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("com/workshop1/miniproject2/views/room-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage() ;
        stage.setTitle("Room Management");
        stage.setScene(scene);
        stage.show();
    }

    public void openBuildingCollabMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("com/workshop1/miniproject2/views/businessCollab-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage() ;
        stage.setTitle("Business Collab Management");
        stage.setScene(scene);
        stage.show();
    }
}
