package com.example.dataanalyticshubfx;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseTable {

    public static void createNewTable() {
        createUsersTable();
        createPostsTable();
    }

    private static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY,\n"
                + " username text NOT NULL UNIQUE,\n"
                + " password text NOT NULL,\n"
                + " first_name text,\n"
                + " last_name text\n"
                + ");";

        try (Connection conn = SQLConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createPostsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS posts (\n"
                + " id integer PRIMARY KEY,\n"
                + " content text NOT NULL,\n"
                + " author text,\n"
                + " likes integer DEFAULT 0,\n"
                + " shares integer DEFAULT 0,\n"
                + " date_time text NOT NULL,\n"
                + " FOREIGN KEY (author_id) REFERENCES users(id)\n"
                + ");";

        try (Connection conn = SQLConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}