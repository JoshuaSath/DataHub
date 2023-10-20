package com.example.dataanalyticshubfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class AfterLogin implements Initializable {

    @FXML
    private Button sortpost;

    @FXML
    private Button removepostBUTTON;

    @FXML
    private Button editprofileBUTTON;

    @FXML
    private Button addPostBUTTON;

    @FXML
    private Button userLogOut;

    @FXML
    private Button retrievepostBUTTON;

    @FXML
    private Label welcomeLABEL;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        userLogOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBOperations.changeScene(event, "LogIn.fxml", null);

            }
        });

    }

    public void setUserInfo(String UserName) {
        welcomeLABEL.setText("Welcome" + " " + UserName + " !");
    }


    public void editprofileBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "editProfile.fxml", null);
    }

    public void addPostBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "addPost.fxml", null);
    }

    public void removePostBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "removePost.fxml", null);
    }

    public void retrievePostBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "retrievePost.fxml", null);
    }

}