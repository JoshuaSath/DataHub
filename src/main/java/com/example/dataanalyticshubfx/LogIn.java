package com.example.dataanalyticshubfx;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import com.example.dataanalyticshubfx.DBOperations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.ResultSet;

import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable {

    @FXML
    private TextField usernameTEXT;

    @FXML
    private TextField passwordFIELD;

    @FXML
    private Button loginBUTTON;

    @FXML
    private Button registerBUTTON;

    @FXML
    private Text wrongLogInTEXT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            loginBUTTON.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    boolean success = DBOperations.logInUser(event, usernameTEXT.getText(), passwordFIELD.getText());
                    if (success) {
                        DBOperations.changeScene(event, "afterLogIn.fxml", usernameTEXT.getText());
                    } else {
                        wrongLogInTEXT.setVisible(true);

                    }
                }
            });

            registerBUTTON.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DBOperations.changeScene(event, "register.fxml", null);
                }
            });


        }

    }
