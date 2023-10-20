package com.example.dataanalyticshubfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.DriverManager;

//MAIN CLASS

public class DataAnalyticsHub extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //stg = primaryStage;
        DatabaseTable.createNewTable();
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        primaryStage.setTitle("DataAnalyticsHub");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}