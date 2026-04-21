package com.virtusa;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("------Welcome to the Quiz App!------");
        System.out.println("Enter your user_name and password to start the quiz:");

        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine().trim();
        String user_password = sc.nextLine().trim();

        if (User.checkUser(user, user_password)) {
            System.out.println("Welcome back, " + user + "!");
        } else {
            if (User.userExists(user)) {
                System.out.println("User already exists, please login with correct password.");
                sc.close();
                return;
            }

            System.out.println("Hello, " + user + "! Let's get started");
            if (User.addUser(user, user_password)) {
                System.out.println("Account created successfully! Welcome, " + user + "!");
            } else {
                System.out.println("Failed to create account. Please try again.");
                sc.close();
                return;
            }
        }

        System.out.println("------Available Subjects:-------");
        System.out.println("1. Java");
        System.out.println("2. Python");
        System.out.println("3. Sql");
        System.out.println("Enter your choice:");

        int n = sc.nextInt();
        Questions q = new Questions();
        List<Questions> questions_list = q.getQuestionsBySubject(n);
        Collections.shuffle(questions_list, new Random());
        List<Questions> wrong_answeredList = new ArrayList<>();
        int score = 0;
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
            } else if (ans == question.RightAnswer) {
                score++;
            } else {
                wrong_answeredList.add(question);
            }
        }

        User.updateScore(user,score);
        System.out.println("Hello, " + user + "! Your final score is: " + score + "/" + questions_list.size());
        if (!wrong_answeredList.isEmpty()) {
            System.out.println("Here are the questions you got wrong/not attempted:");
            int c = 0;
            for (Questions question : wrong_answeredList) {
                c++;
                System.out.println(c + ". " + question.question);
                System.out.println("Correct Answer is: " + question.options[question.RightAnswer - 1]);
            }
        }

        LeaderBoard.displayMyRank(user);
        System.out.println("Enter yes if you want to display the leaderboard");
        String display = sc.next();
        if (display.equalsIgnoreCase("yes")) {
            LeaderBoard.display_leaderboard();
        }
        sc.close();
    }
}