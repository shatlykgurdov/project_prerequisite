package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // set up a database connection
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/example_schema";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
