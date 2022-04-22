package cs151.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// connect to SQLite database
// use singleton design pattern as we only need 1 instance of this class
public class DatabaseConnection {
    private static DatabaseConnection db = new DatabaseConnection();
    // built-in Connection object used to interact with db
    private Connection conn;

    // when this object is instantiated, it will connect to sqlite and
    // retrieve Connection object
    private DatabaseConnection() {
        String db_path = "jdbc:sqlite:src/main/java/cs151/database/passaver.db";
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

    // get DatabaseConnection object
    public static DatabaseConnection getDBConnection()
    {
        return db;
    }
    // get Connection object
    public Connection getConnection() {
        return conn;
    }
}
