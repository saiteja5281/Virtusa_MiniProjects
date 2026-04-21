package com.virtusa;
import java.sql.*;
import com.virtusa.config.DBConnection;

public class LeaderBoard {

    public static void displayMyRank(String user) {
        try (Connection con = DBConnection.getConnection()) {
            String q = "select * from user_table order by score desc";
            PreparedStatement pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            int rank = 1;
            while (rs.next()) {
                if (rs.getString("user_name").equals(user)) {
                    System.out.println("Your rank is: " + rank);
                    break;
                }
                rank++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void display_leaderboard() {
        try (Connection con = DBConnection.getConnection()) {
            String q = "select * from user_table order by score desc";
            PreparedStatement pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery();
            System.out.println("------Leaderboard-------");
            int rank = 1;
            while (rs.next()) {
                System.out.println(rank + ". " + rs.getString("user_name") + " - " + rs.getInt("score"));
                rank++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}