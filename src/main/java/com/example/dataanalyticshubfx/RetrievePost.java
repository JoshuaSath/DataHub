package com.example.dataanalyticshubfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class RetrievePost {

    @FXML
    private TextField postIDTEXT;
    @FXML
    private TextField userIdTEXT;
    @FXML
    private TextField userLikesTEXT;
    @FXML
    private TextField userSharesTEXT;
    @FXML
    private TextField userContentTEXT;
    @FXML
    private TextField userDateTimeTEXT;
    @FXML
    private TextField userAuthorTEXT;
    @FXML
    private Button enterBUTTON;
    @FXML
    private Button backBUTTON;

    public void retrievePost() {
        String ID = postIDTEXT.getText();

        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");
            String query = "SELECT * FROM posts WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userId = resultSet.getString("id");
                String likes = resultSet.getString("likes");
                String shares = resultSet.getString("shares");
                String content = resultSet.getString("content");
                String dateTime = resultSet.getString("date_time");
                String author = resultSet.getString("author");

                // Display the retrieved post data on the UI labels
                userIdTEXT.setText(userId);
                userLikesTEXT.setText(likes);
                userSharesTEXT.setText(shares);
                userContentTEXT.setText(content);
                userDateTimeTEXT.setText(dateTime);
                userAuthorTEXT.setText(author);
            } else {
                // Post not found, clear the UI labels
                userIdTEXT.setText("");
                userLikesTEXT.setText("");
                userSharesTEXT.setText("");
                userContentTEXT.setText("");
                userDateTimeTEXT.setText("");
                userAuthorTEXT.setText("");
                userAuthorTEXT.setText("Post doesnt exist!");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void enterButtonClicked() {
        retrievePost();
    }

    public void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "afterLogIn.fxml", null);
    }
}
