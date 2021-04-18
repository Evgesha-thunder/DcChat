package server;

import javafx.fxml.FXML;

import java.sql.*;

public class DataBaseAuthservice {
    private static Connection connection;
    private static Statement stm;
    private static PreparedStatement psGetNickname;
    private static PreparedStatement psRegistration;
    private static PreparedStatement psChangeNick;


    public static boolean connect() throws ClassNotFoundException, SQLException {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "qwerasdf123");
        stm = connection.createStatement();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    private static void prepareStatementAll() throws SQLException {
        psGetNickname = connection.prepareStatement("SELECT name FROM users WHERE login = ? AND password = ?;");
        psRegistration = connection.prepareStatement("INSERT INTO users (login,password,name) VALUES (?, ?, ?);");
        psChangeNick = connection.prepareStatement("UPDATE users SET name = ? WHERE name = ?;");

    }

    public static String getNickNameByPasswordAndLog(String login, String password) {
        String nick = null;
        try {
            psGetNickname.setString(1, login);
            psGetNickname.setString(2, password);


            ResultSet rs = psGetNickname.executeQuery();
            if (rs.next()) {
                nick = rs.getString(1);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nick;
    }

    public static boolean registration(String liogin, String password, String name) {

        try {
            psRegistration.setString(1, liogin);

            psRegistration.setString(2, password);

            psRegistration.setString(3, name);
            psRegistration.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;

        }
    }

    public static boolean changeNick(String oldNick, String newNick) {
        try {
            psChangeNick.setString(1, newNick);
            psChangeNick.setString(2, oldNick);
            psChangeNick.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;

        }
    }

    public static void disconnect() {
        try {
            psChangeNick.close();
            psGetNickname.close();
            psRegistration.close();
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
