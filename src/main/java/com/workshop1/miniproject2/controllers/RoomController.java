package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.Room;
import com.workshop1.miniproject2.models.RoomStore;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class RoomController {
    @FXML private Button backBtn;
    @FXML private Button insBtn;
    @FXML private TableColumn<Room, Number> rNumberCol;
    @FXML private TextField rNumberFld;
    @FXML private TableColumn<Room, Number> floorCol;
    @FXML private TextField floorFld;
    @FXML private Button delBtn;
    @FXML private TableColumn<Room, String> buildingCol;
    @FXML private TextField buildingFld;
    @FXML private TableView<Room> roomTbl;
    @FXML private Button updtBtn;
    @FXML private Label errorRooms;

    private final RoomStore roomStore = RoomStore.getInstance();

    @FXML
    public void initialize() {
        rNumberCol.setCellValueFactory(cellData -> cellData.getValue().rNumberProperty());
        floorCol.setCellValueFactory(cellData -> cellData.getValue().floorProperty());
        buildingCol.setCellValueFactory(cellData -> cellData.getValue().buildingProperty());

        ObservableList<Room> rooms = roomStore.getRoomsList();
        roomTbl.setItems(rooms);
        errorRooms.setVisible(false);

        roomTbl.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Room selectedRoom = roomTbl.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                rNumberFld.setText(String.valueOf(selectedRoom.getrNumber()));
                floorFld.setText(String.valueOf(selectedRoom.getFloor()));
                buildingFld.setText(selectedRoom.getBuilding());
            }
        });
    }

    @FXML
    public void insertRoom(ActionEvent event) {
        String error = "";
        boolean isValid = true;

        try {
            int rNumber = Integer.parseInt(rNumberFld.getText());
            int floor = Integer.parseInt(floorFld.getText());
            String building = buildingFld.getText();

            if (building.isEmpty()) {
                error += "Building cannot be empty!\n";
                isValid = false;
                errorRooms.setVisible(true);
            }

            if (isValid) {
                roomStore.insertRoom(new Room(rNumber, floor, building));
                roomTbl.setItems(roomStore.getRoomsList());
                roomTbl.refresh();
                clearFields();
                errorRooms.setText("");
                errorRooms.setVisible(false);
            } else {
                errorRooms.setText(error);
            }
        } catch (NumberFormatException e) {
            errorRooms.setText("Room number and floor must be valid numbers!\n" + error);
            errorRooms.setVisible(true);
        }
    }

    @FXML
    public void deleteRoom(ActionEvent event) {
        Room selectedRoom = roomTbl.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            roomStore.deleteRoom(selectedRoom);
            roomTbl.setItems(roomStore.getRoomsList());
            roomTbl.refresh();
            clearFields();
        }
    }

    @FXML
    public void updateRoom(ActionEvent event) {
        Room selectedRoom = roomTbl.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            String error = "";
            boolean isValid = true;

            try {
                int rNumber = Integer.parseInt(rNumberFld.getText());
                int floor = Integer.parseInt(floorFld.getText());
                String building = buildingFld.getText();

                if (building.isEmpty()) {
                    error += "Building cannot be empty!\n";
                    isValid = false;
                    errorRooms.setVisible(true);
                }

                if (isValid) {
                    roomStore.updateRoom(selectedRoom, rNumber, floor, building);
                    roomTbl.refresh();
                    errorRooms.setText("");
                    errorRooms.setVisible(false);
                } else {
                    errorRooms.setText(error);
                }
            } catch (NumberFormatException e) {
                errorRooms.setText("Room number and floor must be valid numbers!\n" + error);
                errorRooms.setVisible(true);
            }
        }
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

    private void clearFields() {
        rNumberFld.clear();
        floorFld.clear();
        buildingFld.clear();
    }
}