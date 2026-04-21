package com.virtusa;

import java.sql.*;
import com.virtusa.config.DBConnection;

public class User {

    public static boolean checkUser(String user, String user_password) {
        try (Connection con = DBConnection.getConnection()) {
            String q = "select * from user_table where user_name=? and user_password=?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, user);
            pst.setString(2, user_password);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean userExists(String user) {
        try (Connection con = DBConnection.getConnection()) {
            String q = "select * from user_table where user_name=?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addUser(String user, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String q = "insert into user_table(user_name,user_password,score) values(?,?,0)";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, user);
            pst.setString(2, password);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateScore(String user, int score){
    try(Connection con=DBConnection.getConnection()){
        System.out.println("Updating score for user: " + user + " with score: " + score);
        String q="update user_table set score=? where user_name=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setInt(1, score);
        pst.setString(2, user);

        int rows = pst.executeUpdate();
        System.out.println("Rows updated: " + rows);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}