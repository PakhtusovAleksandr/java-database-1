package ru.be_prog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseJdbcUtil {
    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "customers";
        String userName = "root";
        String password = "Kali4Kali";

        Class.forName("org.postgresql.Driver");

        String connectionURL = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException
    //Метод для соединения, если требуется присоединиться к другой БД с другими параметрами
    {

        Class.forName("org.postgresql.Driver");

        String connectionURL = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

    // здесь должна располагаться ваша логика настройки соединения с БД

}
