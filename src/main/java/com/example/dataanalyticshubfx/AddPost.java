package com.example.dataanalyticshubfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.ResultSet;

public class AddPost {

    @FXML
    private TextField postid;

    @FXML
    private TextField postcontent;

    @FXML
    private TextField postshares;

    @FXML
    private TextField postdatetime;

    @FXML
    private TextField postlikes;

    @FXML
    private TextField postauthor;

    @FXML
    private Button addpostBUTTON;

    @FXML
    private Label statusText;

    @FXML
    private void addPost() {
        ActionEvent event = null;
        // Get the post data from the input fields
        String Postid = postid.getText();
        String Postcontent = postcontent.getText();
        String Postlikes = postlikes.getText();
        String Postauthor = postauthor.getText();
        String Postdatetime = postdatetime.getText();
        String Postshares = postshares.getText();

        // Add the post to the database

        addPostToData(Postid, Postcontent, Postlikes, Postauthor, Postdatetime, Postshares);
    }

    public static void addPostToData(String id, String content, String likes, String author, String date_time, String shares) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckPostExists = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");

            // Check if the post already exists
            psCheckPostExists = connection.prepareStatement("SELECT * FROM posts WHERE id = ?");//
            psCheckPostExists.setString(1, id);
            resultSet = psCheckPostExists.executeQuery();

            if (resultSet.isBeforeFirst()) {

                System.out.println("Post already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("cannot use Postid");
                alert.show();
                return;
            }

            // Insert the new post into the database
            psInsert = connection.prepareStatement("INSERT INTO posts (id, content, likes, author, date_time, shares) VALUES (?, ?, ?, ?, ?,?)");
            psInsert.setString(1, id);
            psInsert.setString(2, content);
            psInsert.setString(3, likes);
            psInsert.setString(4, author);
            psInsert.setString(5, date_time);
            psInsert.setString(6, shares);
            psInsert.executeUpdate();

            System.out.println("Post added successfully");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Post added successfully");
            alert.show();

            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (psCheckPostExists != null) psCheckPostExists.close();
                if (psInsert != null) psInsert.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}