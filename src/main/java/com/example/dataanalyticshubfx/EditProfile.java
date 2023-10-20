package com.example.dataanalyticshubfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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

    public void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "afterLogIn.fxml", null);
    }

    @FXML
    private void updateprofile() {
        User currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            updateMsgTEXT.setText("User is not logged in.");
            return;
        }

        // Get the updated profile information from the UI elements
        String newFirstName = firstnameTEXT.getText();
        String newLastName = lastnameTEXT.getText();
        String newUsername = usernameTEXT.getText();
        String newPassword = passwordFIELD.getText();

        // Check if the username is already in use
        if (isUsernameTaken(newUsername)) {
            updateMsgTEXT.setText("Username is already in use.");
            return;
        }

        // Get the user's ID from the user object
        int userId = currentUser.getUserId();

        // Check if the user's ID is valid
        if (userId == -1) {
            updateMsgTEXT.setText("Invalid user ID.");
            return;
        }

        // Update the user's profile information in the database
        boolean updateSuccessful = updateUserProfile(userId, newFirstName, newLastName, newUsername, newPassword);

        if (updateSuccessful) {
            updateMsgTEXT.setText("Profile updated successfully.");
            // Update the current user's properties
            currentUser.setFirstName(newFirstName);
            currentUser.setLastName(newLastName);
            currentUser.setUsername(newUsername);
            currentUser.setPassword(newPassword);
        } else {
            updateMsgTEXT.setText("Profile update failed.");

        }
    }

    private boolean updateUserProfile(int userId, String newFirstName, String newLastName, String newUsername, String newPassword) {
        try (Connection conn = SQLConnection.connect()) {
            if (conn != null) {
                String updateQuery = "UPDATE users SET firstname=?, lastname=?, username=?, password=? WHERE user_Id=?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setString(2, newUsername);
                    preparedStatement.setString(3, newPassword);
                    preparedStatement.setString(4, newFirstName);
                    preparedStatement.setString(5, newLastName);

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
                String checkUsernameQuery = "SELECT * FROM users WHERE username=?";

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

    public int getUserId() {


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

    public void loginSuccessful(User user) {
        SessionManager.setCurrentUser(user);
    }
}