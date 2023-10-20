package com.example.dataanalyticshubfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

public class SortPosts implements Initializable {

    @FXML
    private TextField numberInputTEXT;
    @FXML
    private Button enterBUTTON;
    @FXML
    private Button backBUTTON;
    @FXML
    private ListView<String> likesLIST;

    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleEnterButton(ActionEvent event) {
        String numInput = numberInputTEXT.getText();
        int numPosts;

        try {
            numPosts = Integer.parseInt(numInput);
        } catch (NumberFormatException e) {
            return;
        }

        try {
            String query = "SELECT * FROM posts ORDER BY likes DESC LIMIT ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, numPosts);

            ResultSet resultSet = statement.executeQuery();

            likesLIST.getItems().clear();
            while (resultSet.next()) {
                String post = resultSet.getString("content");
                likesLIST.getItems().add(post);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "afterLogIn.fxml", null);
    }
}