package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BusinessCollabStore {
    private final ObservableList<BusinessCollab> businessCollabs = FXCollections.observableArrayList();
    private static final BusinessCollabStore instance = new BusinessCollabStore();

    public BusinessCollabStore() {
        this.businessCollabs.addAll(
                new BusinessCollab("HighTech", "NY", "Product"),
                new BusinessCollab("Lighting", "LA", "Product")
        );
    }

    public static BusinessCollabStore getInstance() {
        return instance;
    }

    public ObservableList<BusinessCollab> getBusinessCollabsList() {
        return businessCollabs;
    }

    public void insertBusinessCollab(BusinessCollab businessCollab) {
        if (businessCollab != null) {
            this.businessCollabs.add(businessCollab);
        }
    }

    public void deleteBusinessCollab(BusinessCollab businessCollab) {
        if (businessCollab != null) {
            this.businessCollabs.remove(businessCollab);
        }
    }

    public void updateBusinessCollab(BusinessCollab businessCollab, String bName, String bLocation, String bType) {
        if (businessCollab != null) {
            businessCollab.setbName(bName);
            businessCollab.setbLocation(bLocation);
            businessCollab.setbType(bType);
        }
    }
}
