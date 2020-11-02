package com.pazyniuk.passive.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BusinessLogic {
    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pazyniuk?useSSL=false", "root", "12345678");
        } catch (SQLException e) {
            printSQLException(e);
        }
        return connection;
    }

    public static void printSQLException(final SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
