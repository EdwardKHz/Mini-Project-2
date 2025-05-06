package com.workshop1.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class RoomStore {

    private ObservableList<Room> rooms = FXCollections.observableArrayList();
    private String dbURL = "jdbc:mysql://localhost:3306/mini-project-2";
    private String dbUsername = "root";
    private String dbPassword = "123456";

    public RoomStore() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Room> getRooms() {
        String query = "SELECT * FROM rooms";
        rooms.clear();
        try (Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("rNumber"),
                        rs.getInt("floor"),
                        rs.getString("building")
                );
                rooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rooms;
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO rooms (rNumber, floor, building) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, room.getrNumber());
            statement.setInt(2, room.getFloor());
            statement.setString(3, room.getBuilding());
            statement.executeUpdate();

            rooms.add(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(Room room) {
        String query = "DELETE FROM rooms WHERE rNumber = ? AND floor = ? AND building = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, room.getrNumber());
            statement.setInt(2, room.getFloor());
            statement.setString(3, room.getBuilding());
            statement.executeUpdate();

            rooms.remove(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room, int newNumber, int newFloor, String newBuilding) {
        String query = "UPDATE rooms SET rNumber = ?, floor = ?, building = ? WHERE rNumber = ? AND floor = ? AND building = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, newNumber);
            statement.setInt(2, newFloor);
            statement.setString(3, newBuilding);
            statement.setInt(4, room.getrNumber());
            statement.setInt(5, room.getFloor());
            statement.setString(6, room.getBuilding());
            statement.executeUpdate();

            room.setrNumber(newNumber);
            room.setFloor(newFloor);
            room.setBuilding(newBuilding);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}