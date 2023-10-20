package com.example.dataanalyticshubfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Register implements Initializable {

    @FXML
    private TextField firstnameTEXT;
    @FXML
    private TextField lastnameTEXT;
    @FXML
    private TextField usernameTEXT;
    @FXML
    private TextField passwordFIELD;

    @FXML
    private TextField IdTEXT;

    @FXML
    private Button backBUTTON;

    @FXML
    private Label reguser;

    @FXML
    private Button register;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!IdTEXT.getText().trim().isEmpty() || !usernameTEXT.getText().trim().isEmpty() || !passwordFIELD.getText().trim().isEmpty() || (firstnameTEXT.getText().trim().isEmpty() || lastnameTEXT.getText().trim().isEmpty())) {
                    DBOperations.registerUser(event, IdTEXT.getText(), usernameTEXT.getText(), passwordFIELD.getText(), firstnameTEXT.getText(), lastnameTEXT.getText());
                    reguser.setText("Successful creation!");
                } else {
                    System.out.println("fill in all nfo");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("fill in info");
                    alert.show();
                }

            }
        });


    }

    public void backBUTTON(ActionEvent event) throws IOException {
        DBOperations.changeScene(event, "logIn.fxml", null);
    }

}