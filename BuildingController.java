package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.Building;
import com.workshop1.miniproject2.models.BuildingStore;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BuildingController {
    @FXML
    private Button bmBackBtn;
    @FXML
    private Button bmInsBtn;
    @FXML
    private TableColumn<Building, String> bmNameCol;
    @FXML
    private TextField bmNameFld;
    @FXML
    private TableColumn<Building, String> bmLocationCol;
    @FXML
    private TextField bmLocationFld;
    @FXML
    private Button bmDelBtn;
    @FXML
    private TableColumn<Building, String> bmFloorCol;
    @FXML
    private TextField bmFloorFld;
    @FXML
    private TableView<Building> bmTbl;
    @FXML
    private Button bmUpdtBtn;
    @FXML
    private Label errorBuilding;

    private final BuildingStore bmStore = BuildingStore.getInstance();

    @FXML
    public void initialize() {
        bmNameCol.setCellValueFactory(new PropertyValueFactory<>("bmName"));
        bmLocationCol.setCellValueFactory(new PropertyValueFactory<>("bmLocation"));
        bmFloorCol.setCellValueFactory(new PropertyValueFactory<>("bmFloor"));

        ObservableList<Building> buildings = bmStore.getBuildingsList();
        bmTbl.setItems(buildings);
        errorBuilding.setVisible(false);

        bmTbl.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Building selectedBuilding = bmTbl.getSelectionModel().getSelectedItem();
            if (selectedBuilding != null) {
                bmNameFld.setText(selectedBuilding.getBmName());
                bmLocationFld.setText(selectedBuilding.getBmLocation());
                bmFloorFld.setText(selectedBuilding.getBmFloor());
            }
        });
    }

    @FXML
    public void insBuilding(ActionEvent event) {
        errorBuilding.setVisible(false);
        errorBuilding.setText("");

        String error = "";
        boolean isValid = true;

        String bmName = bmNameFld.getText().trim();
        if (bmName.isEmpty()) {
            error += "Name cannot be empty!\n";
            isValid = false;
        }

        String bmLocation = bmLocationFld.getText().trim();
        if (bmLocation.isEmpty()) {
            error += "Location cannot be empty!\n";
            isValid = false;
        }

        String bmFloor = bmFloorFld.getText().trim();
        if (bmFloor.isEmpty()) {
            error += "Floor cannot be empty!\n";
            isValid = false;
        }

        if (isValid) {
            bmStore.insertBuilding(new Building(bmName, bmLocation, bmFloor));
            bmTbl.refresh();
            bmNameFld.clear();
            bmLocationFld.clear();
            bmFloorFld.clear();
        } else {
            errorBuilding.setText(error);
            errorBuilding.setVisible(true);
        }
    }

    @FXML
    public void delBuilding(ActionEvent event) {
        Building selectedBuilding = bmTbl.getSelectionModel().getSelectedItem();
        if (selectedBuilding != null) {
            bmStore.deleteBuilding(selectedBuilding);
            bmTbl.refresh();
        }
    }

    @FXML
    public void updtBuilding(ActionEvent event) {
        Building selectedBuilding = bmTbl.getSelectionModel().getSelectedItem();
        if (selectedBuilding != null) {
            String error = "";
            boolean isValid = true;

            String bmName = bmNameFld.getText().trim();
            if (bmName.isEmpty()) {
                error += "Name cannot be empty!\n";
                isValid = false;
                errorBuilding.setVisible(true);
            }

            String bmLocation = bmLocationFld.getText().trim();
            if (bmLocation.isEmpty()) {
                error += "Location cannot be empty!\n";
                isValid = false;
                errorBuilding.setVisible(true);
            }

            String bmFloor = bmFloorFld.getText().trim();
            if (bmFloor.isEmpty()) {
                error += "Floor cannot be empty!\n";
                isValid = false;
                errorBuilding.setVisible(true);
            }

            if (isValid) {
                bmStore.updateBuilding(selectedBuilding, bmName, bmLocation, bmFloor);
                bmTbl.refresh();
                errorBuilding.setText("");
                errorBuilding.setVisible(false);
            } else {
                errorBuilding.setText(error);
            }
        }
    }

    @FXML
    public void backAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) bmBackBtn.getScene().getWindow();
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
