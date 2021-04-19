package server;

import java.sql.*;

public class DataBaseConnection {
    private static Connection connection;
    private static Statement stm;
    private static PreparedStatement getNickName;
    private static PreparedStatement SaveData;
    private static PreparedStatement ChangeNick;

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

    public static void preparePatterns() throws SQLException {
        getNickName = connection.prepareStatement("SELECT name FROM users WHERE login=? AND password =?");
        ChangeNick = connection.prepareStatement("UPDATE users SET newNick=? WHERE oldNick=?");
        SaveData = connection.prepareStatement("INSERT INTO users (?,?,?) VALUES (login,password,nick)");
    }

    public static String getNickNameByLogAndPass(String login, String password) {
        String nick = null;
        try {
            getNickName.setString(1, login);
            getNickName.setString(2, password);


            ResultSet rs = getNickName.executeQuery();
            if (rs.next()) {
                nick = rs.getString(1);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nick;
    }


    public static boolean regist(String login, String password, String nick) {
        try {
            SaveData.setString(1, login);
            SaveData.setString(2, password);
            SaveData.setString(3, nick);

            SaveData.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
return false;

        }
    }

    public static boolean changeNick(String newNick,String oldNick){
        try {
            ChangeNick.setString(1,newNick);
            ChangeNick.setString(2,oldNick);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static void disconnect() {
        try {
           SaveData.close();
           ChangeNick.close();
           getNickName.close();
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
