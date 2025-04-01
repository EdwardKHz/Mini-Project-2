package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomStore {
    private final ObservableList<Room> rooms = FXCollections.observableArrayList();
    private static final RoomStore instance = new RoomStore();

    public RoomStore() {
        this.rooms.addAll(
                new Room(27, 3, "HS")
        new Room(12,4,"Georges Samia")
        );
    }

    public static RoomStore getInstance() {
        return instance;
    }

    public ObservableList<Room> getRoomsList() {
        return rooms;
    }

    public void insertRoom(Room room) {
        if (room != null) {
            this.rooms.add(room);
        }
    }

    public void deleteRoom(Room room) {
        if (room != null) {
            this.rooms.remove(room);
        }
    }

    public void updateRoom(Room room, int rNumber, int floor, String building) {
        if (room != null) {
            room.setrNumber(rNumber);
            room.setFloor(floor);
            room.setBuilding(building);
        }
    }
}