package com.virtusa;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.*;

import com.virtusa.config.DBConnection;

public class Main {

    public static boolean checkUser(String user,String user_password){
    try(Connection con=DBConnection.getConnection()){
        String q="select * from user_table where user_name=? and user_password=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,user);
        pst.setString(2,user_password);
        ResultSet rs=pst.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    }

    public static boolean addUser(String user, String password){
        try(Connection con=DBConnection.getConnection()){
            String q="insert into user_table(user_name,user_password) values(?,?)";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,user);
            pst.setString(2,password);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 

    public static void displayMyRank(String user,int score){
        try(Connection con=DBConnection.getConnection()){
            String q="select * from user_table order by score desc";
            PreparedStatement pst=con.prepareStatement(q);
            ResultSet rs=pst.executeQuery();
            int rank=1;
            while(rs.next()){
                if(rs.getString("user_name").equals(user)){
                    System.out.println("Your rank is: "+rank);
                    break;
                }
                rank++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void display_leaderboard(){
        try(Connection con=DBConnection.getConnection()){
            String q="select * from user_table order by score desc";
            PreparedStatement pst=con.prepareStatement(q);
            ResultSet rs=pst.executeQuery();
            System.out.println("------Leaderboard-------");
            int rank=1;
            while(rs.next()){
                System.out.println(rank+". "+rs.getString("user_name")+" - "+rs.getInt("score"));
                rank++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("------Welcome to the Quiz App!------");
        System.out.println("Enter your user_name and password to start the quiz:");
        Scanner sc=new Scanner(System.in);
        String user=sc.nextLine();
        String user_password=sc.nextLine();
        if(checkUser(user,user_password)){
            System.out.println("Welcome back, " + user + "!");
        }
        else{
            System.out.println("Hello, " + user + "! Let's get started");
            if(addUser(user, user_password)){
                System.out.println("Account created successfully! Welcome, " + user + "!");
            }
            else{
                System.out.println("Failed to create account. Please try again.");
                sc.close();
                System.exit(0);
            }
        }

        
        System.out.println("------Available Subjects:-------");
        System.out.println("1. Java");
        System.out.println("2. Python");
        System.out.println("3. Sql");
        System.out.println("Enter your choice:");

        int n=sc.nextInt();
        Questions q=new Questions();
        List<Questions> questions_list=q.getQuestionsBySubject(n);
        Collections.shuffle(questions_list, new Random());
        List<Questions>wrong_answeredList=new ArrayList<>();
        System.out.println(questions_list);
        int score=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (Questions question : questions_list) {
            System.out.println(question.question);
            for (int i = 0; i < 4; i++) {
                System.out.println((i + 1) + ". " + question.options[i]);
            }
            System.out.println("Enter answer in 10 sec:");
            long start = System.currentTimeMillis();
            int ans = -1;

            while ((System.currentTimeMillis() - start) < 10000) {
                if (br.ready()) {
                    ans = Integer.parseInt(br.readLine());
                    break;
                }
            }


            if (ans == -1) {
                System.out.println("Time's up!");
                wrong_answeredList.add(question);
            } 
            else if (ans == question.RightAnswer) score++;
            else {
                wrong_answeredList.add(question);
            }
        }

        System.out.println("Hello, " + user + "! Your final score is: " + score + "/" + questions_list.size());

        if (!wrong_answeredList.isEmpty()) {
            System.out.println("Here are the questions you got wrong/not attempted:");
            int c=0;
            for (Questions question : wrong_answeredList) {
                c++;
                System.out.println(c+". "+question.question);
                System.out.println("Correct Answer is: " + question.options[question.RightAnswer - 1]);
            }
        }

        displayMyRank(user,score);
        System.out.println("Enter yes if you want to display the leaderboarrd");
        String display=sc.next();
        if(display.equalsIgnoreCase("yes")) display_leaderboard();
        sc.close();
        System.exit(0);
    }
}