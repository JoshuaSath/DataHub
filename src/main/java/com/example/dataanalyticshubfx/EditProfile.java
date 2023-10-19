package com.example.dataanalyticshubfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditProfile {

    @FXML
    private TextField firstnameTEXT;
    @FXML
    private TextField lastnameTEX;
    @FXML
    private TextField usernameTEXT;
    @FXML
    private PasswordField passwordFIELD;
    @FXML
    private Button updateprofile;
    @FXML
    private Button backBUTTON;


    private void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "afterLogIn.fxml", null);
    }



}