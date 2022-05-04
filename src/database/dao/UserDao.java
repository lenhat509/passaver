/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * UserDao class
 */

package database.dao;

import database.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//UserDao aka UserDB
public class UserDao {
    private Connection conn;

    /**
     * UserDao constructor
     * @param conn
     */
    public UserDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * search if a user exist (for login)
     * return null if user does not exist
     * @param username
     * @return
     */
    public User search(String username) {
        String query = "SELECT * FROM user WHERE userId = ?";
        PreparedStatement ps = null;
        User user = null;
        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, username);
            if(ps.execute())
            {
                ResultSet users = ps.getResultSet();
                String userId = users.getString("userId");
                String password = users.getString("password");
                String securityQuestion = users.getString("securityQuestion");
                String securityAnswer = users.getString("securityAnswer");
                user = new User(userId, password, securityQuestion, securityAnswer);
            }
        }
        catch (SQLException e) {
        }
        return user;
    }

    /**
     * add a user to database (foo sign up)
     * @param newUser
     */
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

    /**
     * reset password of a user
     * @param userId
     * @param newPassword
     */
    public void resetPassword(String userId, String newPassword) {
        String query = "UPDATE user SET password = ? WHERE userId = ?";
        PreparedStatement ps;

        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, userId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
