package com.example.dataanalyticshubfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Register {

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button back;


    public void userLogIn(ActionEvent event) throws IOException {
        backOne();
    }

    private void backOne() throws IOException {
        DataAnalyticsHub m = new DataAnalyticsHub();
        m.changeScene("login.fxml");

    }


}
