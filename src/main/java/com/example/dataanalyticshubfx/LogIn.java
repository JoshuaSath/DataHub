package com.example.dataanalyticshubfx;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class LogIn {

    public LogIn() {


    }

    @FXML
    private TextField username;
    @FXML
    private Text wrongLogIn;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        DataAnalyticsHub m = new DataAnalyticsHub();
        if (username.getText().toString().equals("deez") && password.getText().toString().equals("deez")) {
            wrongLogIn.setText("suss");
            m.changeScene("afterLogin.fxml");


        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data.");

        } else {
            wrongLogIn.setText("Incorrect username or password!");

        }
    }

    public void registerButton(ActionEvent event) throws IOException {
        registerUser();
    }

    private void registerUser() throws IOException {
        DataAnalyticsHub m = new DataAnalyticsHub();
        m.changeScene("register.fxml");

    }

}
