package com.example.dataanalyticshubfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.ResultSet;

public class AddPost {

    @FXML
    private TextField postid;

    @FXML
    private TextField postcontent;

    @FXML
    private TextField postshares;

    @FXML
    private TextField postdatatime;

    @FXML
    private TextField postlikes;

    @FXML
    private TextField postauthor;



}
