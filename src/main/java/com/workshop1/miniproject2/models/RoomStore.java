package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class RoomStore {
    private ObservableList<Room> rooms = FXCollections.observableArrayList();
    private static final RoomStore instance = new RoomStore();

    private RoomStore() {
        getRoomsList();
    }

    public static RoomStore getInstance() {
        return instance;
    }

    public ObservableList<Room> getRoomsList() {
        String query = "SELECT * FROM rooms";
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            rooms.clear();
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("rNumber"),
                        rs.getInt("floor"),
                        rs.getString("building")
                );
                rooms.add(room);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rooms;
    }

    public void insertRoom(Room room) {
        if (room != null) {
            String query = "INSERT INTO rooms (rNumber, floor, building) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement statement = conn.prepareStatement(query)) {

                statement.setInt(1, room.getrNumber());
                statement.setInt(2, room.getFloor());
                statement.setString(3, room.getBuilding());
                statement.executeUpdate();
                rooms.add(room);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteRoom(Room room) {
        if (room != null) {
            String query = "DELETE FROM rooms WHERE rNumber = ? AND floor = ? AND building = ?";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement statement = conn.prepareStatement(query)) {

                statement.setInt(1, room.getrNumber());
                statement.setInt(2, room.getFloor());
                statement.setString(3, room.getBuilding());
                statement.executeUpdate();
                rooms.remove(room);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateRoom(Room room, int rNumber, int floor, String building) {
        if (room != null) {
            String query = "UPDATE rooms SET rNumber = ?, floor = ?, building = ? WHERE rNumber = ? AND floor = ? AND building = ?";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement statement = conn.prepareStatement(query)) {

                statement.setInt(1, rNumber);
                statement.setInt(2, floor);
                statement.setString(3, building);
                statement.setInt(4, room.getrNumber());
                statement.setInt(5, room.getFloor());
                statement.setString(6, room.getBuilding());

                statement.executeUpdate();

                room.setrNumber(rNumber);
                room.setFloor(floor);
                room.setBuilding(building);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}