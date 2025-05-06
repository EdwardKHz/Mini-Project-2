package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.Room;
import com.workshop1.miniproject2.models.RoomStore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
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

    private RoomStore roomStore;

    public RoomController() {
        this.roomStore = new RoomStore();
    }

    @FXML
    public void initialize() {
        // Set cell value factories without lambdas
        rNumberCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Room, Number> cellData) {
                return cellData.getValue().rNumberProperty();
            }
        });

        floorCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Room, Number> cellData) {
                return cellData.getValue().floorProperty();
            }
        });

        buildingCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Room, String> cellData) {
                return cellData.getValue().buildingProperty();
            }
        });

        ObservableList<Room> rooms = roomStore.getRooms();
        roomTbl.setItems(rooms);
        errorRooms.setVisible(false);

        // Selection listener without lambda
        roomTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Room>() {
            @Override
            public void changed(ObservableValue<? extends Room> observable, Room oldValue, Room newValue) {
                if (newValue != null) {
                    rNumberFld.setText(String.valueOf(newValue.getrNumber()));
                    floorFld.setText(String.valueOf(newValue.getFloor()));
                    buildingFld.setText(newValue.getBuilding());
                }
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
                Room newRoom = new Room(rNumber, floor, building);
                roomStore.addRoom(newRoom);
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