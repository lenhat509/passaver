/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * AccountDao class
 */

package database.dao;

import database.models.Account;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AccountDao{
    private Connection conn;

    /**
     * AccountDao's constructor
     * @param conn
     */
    public AccountDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * get all accounts of a user from the database
     * @param userId
     * @return
     */
    public ArrayList<Account> getAccounts(String userId) {
        String query = "SELECT * FROM account WHERE userId = ?";
        ArrayList<Account> accountList = new ArrayList<Account>() ;
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, userId);
            if(ps.execute()) {
                ResultSet accounts = ps.getResultSet();
                while(accounts.next()) {
                    String accountId = accounts.getString("accountId");
                    String appName = accounts.getString("appName");
                    String email = accounts.getString("email");
                    String password = accounts.getString("password");
                    String username = accounts.getString("username");
                    LocalDate creationDate = LocalDate.parse(accounts.getString("creationDate"));
                    LocalDate expirationDate = LocalDate.parse(accounts.getString("expirationDate"));
                    Account account = new Account(accountId, appName, username, email, expirationDate, creationDate, password);
                    accountList.add(account);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accountList;


    }

    /**
     * save an account to database
     * @param loginUserId
     * @param account
     */
    public void saveAccount(String loginUserId, Account account) {
        String query = "INSERT INTO account VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, account.getAccountId());
            ps.setString(2, account.getAppName());
            ps.setString(3, account.getUsername());
            ps.setString(4, account.getPassword());
            ps.setString(5, account.getEmail());
            ps.setString(6, account.getExpirationDate().toString());
            ps.setString(7, account.getCreationDate().toString());
            ps.setString(8, loginUserId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * edit an account
     * @param modifiedAccount
     */
    public void editAccount(Account modifiedAccount) {
        String query = "UPDATE account SET appName = ?," +
                        "email = ?," +
                        "password = ?," +
                        "creationDate = ?," +
                        "expirationDate = ?," +
                        "username = ?" +
                        "WHERE accountId = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, modifiedAccount.getAppName());
            ps.setString(2, modifiedAccount.getEmail());
            ps.setString(3, modifiedAccount.getPassword());
            ps.setString(4, modifiedAccount.getCreationDate().toString());
            ps.setString(5, modifiedAccount.getExpirationDate().toString());
            ps.setString(6, modifiedAccount.getUsername());
            ps.setString(7, modifiedAccount.getAccountId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * delete an account
     * @param accoundId
     */
    public void deleteAccount(String accoundId) {
        String query = "DELETE FROM account WHERE accountId = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, accoundId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search a list of accounts whose appName and username match
     * with a keyword
     * @param userId
     * @param keyword
     * @return
     */
    public ArrayList<Account> searchAccounts(String userId, String keyword) {
        String query = "SELECT * FROM account WHERE userId = ? AND (appName LIKE ? OR username LIKE ?) ";
        ArrayList<Account> accountList = new ArrayList<Account>() ;
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, '%' + keyword + '%');
            ps.setString(3, '%' + keyword + '%');
            if(ps.execute()) {
                ResultSet accounts = ps.getResultSet();
                while(accounts.next()) {
                    String accountId = accounts.getString("accountId");
                    String appName = accounts.getString("appName");
                    String email = accounts.getString("email");
                    String password = accounts.getString("password");
                    String username = accounts.getString("username");
                    LocalDate creationDate = LocalDate.parse(accounts.getString("creationDate"));
                    LocalDate expirationDate = LocalDate.parse(accounts.getString("expirationDate"));
                    Account account = new Account(accountId, appName, username, email, expirationDate, creationDate, password);
                    accountList.add(account);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accountList;
    }

    /**
     * get all the accounts whose expiration date passed a specific date.
     * @param userId
     * @param date
     * @return
     */
    public ArrayList<Account> checkExpiredPassword(String userId, LocalDate date) {
        String query = "SELECT * FROM account WHERE userId = ? AND date(expirationDate) <= date(?); ";
        ArrayList<Account> accountList = new ArrayList<Account>() ;
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, date.toString());
            if(ps.execute()) {
                ResultSet accounts = ps.getResultSet();
                while(accounts.next()) {
                    String accountId = accounts.getString("accountId");
                    String appName = accounts.getString("appName");
                    String email = accounts.getString("email");
                    String password = accounts.getString("password");
                    String username = accounts.getString("username");
                    LocalDate creationDate = LocalDate.parse(accounts .getString("creationDate"));
                    LocalDate expirationDate = LocalDate.parse(accounts.getString("expirationDate"));
                    Account account = new Account(accountId, appName, username, email, expirationDate, creationDate, password);
                    accountList.add(account);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accountList;
    }

    /**
     * get an account
     * @param id
     * @return
     */
    public Account getAccount(String id) {
        String query = "SELECT * FROM account WHERE accountId = ?";
        Account account = null;
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, id);
            if(ps.execute()) {
                ResultSet accounts = ps.getResultSet();
                String accountId = accounts.getString("accountId");
                String appName = accounts.getString("appName");
                String email = accounts.getString("email");
                String password = accounts.getString("password");
                String username = accounts.getString("username");
                LocalDate creationDate = LocalDate.parse(accounts.getString("creationDate"));
                LocalDate expirationDate = LocalDate.parse(accounts.getString("expirationDate"));
                account = new Account(accountId, appName, username, email, expirationDate, creationDate, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }
}