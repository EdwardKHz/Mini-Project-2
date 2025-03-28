package com.workshop1.miniproject2.controllers;

import com.workshop1.miniproject2.models.BusinessCollab;
import com.workshop1.miniproject2.models.BusinessCollabStore;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;


public class BusinessCollabController {
    @FXML
    private Button backBtn;
    @FXML
    private Button insBtn;
    @FXML
    private TableColumn<BusinessCollab, SimpleStringProperty> bNameCol;
    @FXML
    private TextField bNameFld;
    @FXML
    private TableColumn<BusinessCollab, SimpleStringProperty> bLocationCol;
    @FXML
    private TextField bLocationFld;
    @FXML
    private Button delBtn;
    @FXML
    private TableColumn<BusinessCollab, SimpleStringProperty> bTypeCol;
    @FXML
    private TextField bTypeFld;
    @FXML
    private TableView<BusinessCollab> collabTbl;
    @FXML
    private Button updtBtn;
    @FXML
    private Text errorCollab;

    private final BusinessCollabStore bCollabStore = new BusinessCollabStore();

    @FXML
    public void initialize() {
        bNameCol.setCellValueFactory(new PropertyValueFactory<>("bName"));
        bLocationCol.setCellValueFactory(new PropertyValueFactory<>("bLocation"));
        bTypeCol.setCellValueFactory(new PropertyValueFactory<>("bType"));


        ObservableList<BusinessCollab> businessCollabs = BusinessCollabStore.getInstance().getBusinessCollabsList();

        collabTbl.setItems(businessCollabs);

        collabTbl.getSelectionModel().selectedItemProperty().addListener(evt -> {
            BusinessCollab selectedBusiness = collabTbl.getSelectionModel().getSelectedItem();
            if(selectedBusiness != null) {
                bNameFld.setText(selectedBusiness.getbName());
                bLocationFld.setText(selectedBusiness.getbLocation());
                bTypeFld.setText(selectedBusiness.getbType());
            }
        });
    }

    @FXML
    public void insBusinessCollab(ActionEvent event) {
        String error="";
        boolean isValid=true;

        String bName = bNameFld.getText();
        if(bName.isEmpty()) {
            error+="Name cannot be empty!\n";
            isValid=false;
        }

        String bLocation = bLocationFld.getText();
        if(bLocationFld.getText().isEmpty()) {
            error+="Location cannot be empty!\n";
            isValid=false;
        }
        String bType = bTypeFld.getText();
        if(bType.isEmpty()) {
            error+="Type cannot be empty!\n";
            isValid=false;
        }
        if(isValid) {
            bCollabStore.insertBusinessCollab(new BusinessCollab(bName, bLocation, bType));
            collabTbl.setItems(bCollabStore.getBusinessCollabsList());
            collabTbl.refresh();
            bNameFld.clear();
            bLocationFld.clear();
            bTypeFld.clear();
            errorCollab.setText("");

        }
        else {
            errorCollab.setText(error);
        }
    }
    @FXML
    public void delBusinessCollab(ActionEvent event) {
        BusinessCollab selectedBusiness = collabTbl.getSelectionModel().getSelectedItem();
        if (selectedBusiness != null) {
            bCollabStore.deleteBusinessCollab(selectedBusiness);
            collabTbl.setItems(bCollabStore.getBusinessCollabsList());
            collabTbl.refresh();
        }
    }
    @FXML
    public void updtBusinessCollab(ActionEvent event) {
        BusinessCollab selectedBusiness = collabTbl.getSelectionModel().getSelectedItem();
        if(selectedBusiness != null) {
            String error="";
            boolean isValid=true;

            String bName = bNameFld.getText();
            if(bName.isEmpty()) {
                error+="Name cannot be empty!\n";
                isValid=false;
            }
            String bLocation = bLocationFld.getText();
            if(bLocation.isEmpty()) {
                error+="Location cannot be empty!\n";
                isValid=false;
            }
            String bType = bTypeFld.getText();
            if(bType.isEmpty()) {
                error+="Type cannot be empty!\n";
            }
            if(isValid) {
                bCollabStore.updateBusinessCollab(selectedBusiness, bName, bLocation, bType);
                collabTbl.refresh();
                errorCollab.setText("");
            }
            else {
                errorCollab.setText(error);
            }
        }
    }
}
