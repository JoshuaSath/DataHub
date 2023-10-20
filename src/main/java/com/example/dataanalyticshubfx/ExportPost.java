package com.example.dataanalyticshubfx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ExportPost implements Initializable {

    @FXML
    private Button enterButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField postIdTextField;

    @FXML
    private void handleEnter(ActionEvent event) {
        String postId = postIdTextField.getText();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            String outputPath = file.getAbsolutePath();
            exportPostToCsv(postId, outputPath);
        } else {

        }
    }

    private void exportPostToCsv(String postId, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");

            if (conn != null) {
                String sql = "SELECT p.*, c.content " +
                        "FROM posts p " +
                        "JOIN contents c ON p.content_id = c.id " +
                        "WHERE p.id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, postId);

                ResultSet resultSet = preparedStatement.executeQuery();

                writer.write("postId,postContent,author,postLikes,postShares,date_time");
                writer.newLine();

                while (resultSet.next()) {
                    String postContent = resultSet.getString("content");
                    String author = resultSet.getString("author");
                    String date_time = resultSet.getString("date_time");
                    String postShares = resultSet.getString("shares");
                    String postLikes = resultSet.getString("likes");

                    writer.write(postId + "," + postContent + "," + author + "," + postLikes + "," + postShares + "," + date_time);
                    writer.newLine();
                }

                resultSet.close();
                preparedStatement.close();

                writer.close();

                conn.close();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "afterLogIn.fxml", null);
    }
}