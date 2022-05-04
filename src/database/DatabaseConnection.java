package database;

/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * Database connection class
 * connect to SQLite database
 * use singleton design pattern as we only need 1 instance of this class
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection db = new DatabaseConnection();
    private Connection conn;

    /**
     *  when this object is instantiated, it will connect to sqlite and
     *  retrieve Connection object
     */
    private DatabaseConnection() {
        String db_path = "jdbc:sqlite:src/database/passaver.db";
        try
        {
            Class.forName("org.sqlite.JDBC");
            //set Connection object
            this.conn = DriverManager.getConnection(db_path);
        }
         catch (ClassNotFoundException | SQLException e) {
             System.out.println(e.getMessage());
        }
    }

    /**
     * get DatabaseConnection object
     * @return
     */
    public static DatabaseConnection getDBConnection()
    {
        return db;
    }

    /**
     * get Connection object
     * @return
     */
    public Connection getConnection() {
        return conn;
    }
}
