package ru.be_prog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseJdbcUtil {
    public static final String dbName = "accounts";
    public static final String userName = "account_service";
    public static final String password = "account_service";
    public static final String connectionURL = "jdbc:postgresql://localhost:5432/" + dbName;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
