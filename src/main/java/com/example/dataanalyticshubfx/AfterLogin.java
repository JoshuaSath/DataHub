package com.example.dataanalyticshubfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class AfterLogin implements Initializable {

    @FXML
    private Button sortpost;

    @FXML
    private Button removepost;

    @FXML
    private Button editprofile;

    @FXML
    private Button addpost;

    @FXML
    private Button userLogOut;

    @FXML
    private Button retrievepost;

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
        welcomeLABEL.setText("Welcome" + UserName + "!");
    }

    // public void userLogOut(ActionEvent event) throws IOException {
    //     DataAnalyticsHub m = new DataAnalyticsHub();
    // m.changeScene("logIn.fxml");
    //  }


    public void editprofile(ActionEvent event) throws IOException {
        editProfileButton();
    }

    private void editProfileButton() throws IOException {
        DataAnalyticsHub m = new DataAnalyticsHub();
        //m.changeScene("editProfile.fxml");

    }


}