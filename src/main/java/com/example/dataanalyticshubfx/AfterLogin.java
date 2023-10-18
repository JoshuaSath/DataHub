package com.example.dataanalyticshubfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AfterLogin {


    @FXML
    private Button logoutButton;

    @FXML
    private TextField welcomeText;

    @FXML
    private TextArea writepostArea;

    @FXML
    private Button addpostButton;

    @FXML
    private Button userlogoutButton;

    public void userLogOut(ActionEvent event) throws IOException {
        DataAnalyticsHub m = new DataAnalyticsHub();
        m.changeScene("logIn.fxml");
    }


}

