package com.revature.reimbursement.util.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Properties props = new Properties();

    private ConnectionFactory() {
        try {
            /*System.out.println("Another try.\n\n");
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current dir using System:" + currentDir);*/
            props.load(new FileReader("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

        if (conn == null) {
            throw new RuntimeException("Could not establish connection with the database!");
        }

        return conn;
    }
}
