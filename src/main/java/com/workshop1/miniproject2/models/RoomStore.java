package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RoomStore {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/productdb";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword";

    public static ObservableList<Room> getAllRooms() {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        String query = "SELECT * FROM rooms";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("rNumber"),
                        rs.getInt("floor"),
                        rs.getString("building")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public static void addRoom(Room room) {
        String query = "INSERT INTO rooms (rNumber, floor, building) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, room.getrNumber());
            stmt.setInt(2, room.getFloor());
            stmt.setString(3, room.getBuilding());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRoom(Room room) {
        String query = "DELETE FROM rooms WHERE rNumber = ? AND floor = ? AND building = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, room.getrNumber());
            stmt.setInt(2, room.getFloor());
            stmt.setString(3, room.getBuilding());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRoom(Room room, int newNumber, int newFloor, String newBuilding) {
        String query = "UPDATE rooms SET rNumber = ?, floor = ?, building = ? WHERE rNumber = ? AND floor = ? AND building = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, newNumber);
            stmt.setInt(2, newFloor);
            stmt.setString(3, newBuilding);
            stmt.setInt(4, room.getrNumber());
            stmt.setInt(5, room.getFloor());
            stmt.setString(6, room.getBuilding());

            stmt.executeUpdate();

            room.setrNumber(newNumber);
            room.setFloor(newFloor);
            room.setBuilding(newBuilding);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}