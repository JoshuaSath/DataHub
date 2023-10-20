package com.example.dataanalyticshubfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RemovePost {

    @FXML
    private TextField postIDTEXT;

    @FXML
    private Button deleteBUTTON;

    @FXML
    private Button backBUTTON;

    @FXML
    private Label deletemsgLABEL;

    @FXML
    private void deletePost(ActionEvent event) {
        String postId = postIDTEXT.getText();

        if (postId.isEmpty()) {
            deletemsgLABEL.setText("Please enter a post ID");
            return;
        }

        deletePostFromData(postId);
    }

    public void deletePostFromData(String postId) {
        Connection connection = null;
        PreparedStatement psDelete = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");

            psDelete = connection.prepareStatement("DELETE FROM posts WHERE id = ?");
            psDelete.setString(1, postId);
            int rowsAffected = psDelete.executeUpdate();

            if (rowsAffected > 0) {
                deletemsgLABEL.setText("Post deleted successfully");
            } else {
                deletemsgLABEL.setText("Post not found");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if (psDelete != null) psDelete.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "afterLogIn.fxml", null);
    }
}