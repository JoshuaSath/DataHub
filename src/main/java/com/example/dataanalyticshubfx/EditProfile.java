package com.example.dataanalyticshubfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;


public class EditProfile {

    @FXML
    private TextField firstnameTEXT;
    @FXML
    private TextField lastnameTEXT;
    @FXML
    private TextField usernameTEXT;
    @FXML
    private PasswordField passwordFIELD;
    @FXML
    private Button updateprofileBUTTON;
    @FXML
    private Button backBUTTON;

    @FXML
    private Text updateMsgTEXT;


    @FXML
    private void updateprofile() {
        int userId = getUserId();
        if (userId == -1) {
            updateMsgTEXT.setText("User is not logged in.");
            return;
        }

        // Get the updated profile information from the UI elements
        String newFirstName = firstnameTEXT.getText();
        String newLastName = lastnameTEXT.getText();
        String newUsername = usernameTEXT.getText();
        String newPassword = passwordFIELD.getText();

        // Perform database update
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C://Users//Josh1//IdeaProjects//DataAnalyticsHubFX//DataBase.db");
            String query = "UPDATE users SET FirstName = ?, LastName = ?, UserName = ?, Password = ?, WHERE Id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setString(3, newUsername);
            statement.setString(4, newPassword);
            statement.setInt(5, userId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                updateMsgTEXT.setText("Profile updated successfully.");
            } else {
                updateMsgTEXT.setText("Failed to update profile. Please try again later.");
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            updateMsgTEXT.setText("Completed");
            e.printStackTrace();
        }
    }


    private boolean updateUserProfile(int userId, String newFirstName, String newLastName, String newUsername, String newPassword) {
        try (Connection conn = SQLConnection.connect()) {
            if (conn != null) {
                String updateQuery = "UPDATE users SET Firstname=?, LastName=?, UserName=?, Password=? WHERE Id=?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, newFirstName);
                    preparedStatement.setString(2, newLastName);
                    preparedStatement.setString(3, newUsername);
                    preparedStatement.setString(4, newPassword);
                    preparedStatement.setInt(5, userId);

                    int rowsAffected = preparedStatement.executeUpdate();

                    return rowsAffected > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean isUsernameTaken(String newUsername) {
        try (Connection conn = SQLConnection.connect()) {
            if (conn != null) {
                String checkUsernameQuery = "SELECT * FROM users WHERE UserName=?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(checkUsernameQuery)) {
                    preparedStatement.setString(1, newUsername);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private int getUserId() {
        User currentUser = SessionManager.getCurrentUser();

        if (currentUser != null) {
            return currentUser.getUserId();
        }

        return -1;
    }

    public class SessionManager {
        private static User currentUser;

        public static void setCurrentUser(User user) {
            currentUser = user;
        }

        public static User getCurrentUser() {
            if (currentUser == null) {
                currentUser = new User();
            }

            return currentUser;
        }
    }

    public void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "afterLogIn.fxml", null);
    }

    public void loginSuccessful(User user) {
        SessionManager.setCurrentUser(user);
    }
}