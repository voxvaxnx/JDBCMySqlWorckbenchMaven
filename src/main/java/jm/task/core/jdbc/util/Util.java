package jm.task.core.jdbc.util;

import java.net.URL;
import java.sql.*;

public class Util {


        static {



                try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                }
        }
        public static Connection getConnection() {
                final String URL = "jdbc:mysql://localhost:3306/testdb";
                final String USERNAME = "root";
                final String PASS = "root";
                try {
                        Connection conn = DriverManager.getConnection(URL, USERNAME, PASS);
                        return conn;
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }


        }
}




                //private static final Connection connection;


       /* private static final Driver driver;

        static {
                try {
                        driver = new com.mysql.cj.jdbc.Driver();
                        DriverManager.registerDriver(driver);
                        connection = DriverManager.getConnection(URL, USERNAME, PASS);
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        public static Connection getConnection() {
                return connection;
        }*/
