package com.example.dataanalyticshubfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBOperations {

    public static void changeScene(ActionEvent event, String fxmlFile, String UserName) {
        Parent root = null;

        if (UserName != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBOperations.class.getResource(fxmlFile));
                root = loader.load();
                AfterLogin afterlogin = loader.getController();
                afterlogin.setUserInfo((UserName));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBOperations.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void registerUser(ActionEvent event, String Id, String UserName, String Password, String FirstName, String LastName) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM Users WHERE UserName = ?");
            psCheckUserExists.setString(1, UserName);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("cannot use username");
                alert.show();
                return;

            }
            psInsert = connection.prepareStatement("INSERT INTO Users ( Id, UserName, Password, FirstName, LastName) VALUES (?, ?, ?, ?,?)");
            psInsert.setString(1, Id);
            psInsert.setString(2, UserName);
            psInsert.setString(3, Password);
            psInsert.setString(4, FirstName);
            psInsert.setString(5, LastName);
            psInsert.executeUpdate();

            changeScene(event, "afterlogin.fxml", UserName);


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (psCheckUserExists != null) psCheckUserExists.close();
                if (psInsert != null) psInsert.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    public static boolean logInUser(ActionEvent event, String UserName, String Password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");
            preparedStatement = connection.prepareStatement("SELECT Password FROM Users WHERE UserName =?");
            preparedStatement.setString(1, UserName);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("user not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("incorrect creds");
                alert.show();
                return false;
            } //else
            while (resultSet.next()) {
                String retrievedPassword = resultSet.getString("Password");
                if (retrievedPassword.equals(Password)) {
                    changeScene(event, "afterLogIn.fxml", UserName);

                } else {
                    System.out.println("Password not mtach");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password did not match");
                    alert.show();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


}