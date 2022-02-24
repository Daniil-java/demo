package ru.spring.demo;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement statement;

    static void connection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:demo.db");
            statement = connection.createStatement();
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean setNewUser(String login, String password, String email) throws SQLException { //return true if new was set NU
        if (!isLoginBusy(login)) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (login, password, email) VALUES (?, ?, ?)");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.execute();
            return true;
        }
        return false;
    }

    public static String getUser(String login) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users where login = ?");
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        return "id: " + rs.getString("id") + "\n login: " + rs.getString("login");
    }

    public static boolean isLoginBusy(String login) {   //return true if login busy
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT id FROM users where login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    static boolean getAccess(String login, String password) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT id FROM users where login = ? and password = ?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static int findUserId(String login) throws SQLException {
        PreparedStatement ps = null;
        ps = connection.prepareStatement("SELECT id FROM users where login = ?");
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        return rs.getInt("id");
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate(
                "create table if not exists users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "login TEXT UNIQUE," +
                        "password TEXT," +
                        "email TEXT" +
                        ")"
        );
    }

}
