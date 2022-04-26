package cs151.database.dao;

import cs151.database.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// User Data Access Object
public class UserDao {
    private Connection conn;
    // inject Connection object
    public UserDao(Connection conn) {
        this.conn = conn;
    }

    //search if a user exist (for login)
    public User search(String username) {
        // SQL Query template
        String query = "SELECT * FROM user WHERE userId = ?";
        // Java Query Object
        PreparedStatement ps = null;
        User user = null;
        try {
            // initialize Java Query Object with the template
            ps = this.conn.prepareStatement(query);
            // fill the template
            ps.setString(1, username);
            // call query on the database, check if user exist
            if(ps.execute())
            {
                //user found
                //Get all the results
                ResultSet users = ps.getResultSet();
                //extract data from result
                String userId = users.getString("userId");
                String password = users.getString("password");
                String securityQuestion = users.getString("securityQuestion");
                String securityAnswer = users.getString("securityAnswer");
                //create user object for returning
                user = new User(userId, password, securityQuestion, securityAnswer);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public void addUser(User newUser) {
        String query = "INSERT INTO user VALUES(?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, newUser.getUserId());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getSecurityQuestion());
            ps.setString(4, newUser.getSecurityAnswer());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
