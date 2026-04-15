package com.virtusa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.virtusa.config.DBConnection;
import java.util.*;

public class Questions {
    String question;
    String[] options;
    int RightAnswer;
    int subject_id = 0;
    Questions(){

    }
    Questions(String question, String[] options, int RightAnswer,int subject_id) {
        this.question = question;
        this.options = options;
        this.RightAnswer = RightAnswer;
        this.subject_id = subject_id;
    }
    public  List<Questions> getQuestionsBySubject(int subject_id) {
        List<Questions> questions_list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM questions WHERE subject_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, subject_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String question_stmt = rs.getString("question");
                String[] options = new String[4];
                options[0] = rs.getString("option1");
                options[1] = rs.getString("option2");
                options[2] = rs.getString("option3");
                options[3] = rs.getString("option4");
                int rightAnswer = rs.getInt("correct");
                questions_list.add(new Questions(question_stmt, options, rightAnswer, subject_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions_list;
    }
}


