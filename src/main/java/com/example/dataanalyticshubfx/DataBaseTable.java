package com.example.dataanalyticshubfx;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseTable {

    public static void createNewTable() {
        createUsersTable();
    }

    private static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + " Id integer PRIMARY KEY,\n"
                + " UserName text NOT NULL UNIQUE,\n"
                + " Password text NOT NULL,\n"
                + " FirstName text,\n"
                + " LastName text\n"
                + ");";

        try (Connection conn = SQLConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}