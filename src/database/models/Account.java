/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * Account class
 */

package database.models;

import java.time.LocalDate;
import java.util.UUID;

public class Account {

    private String accountId;
    private String appName;
    private String username;
    private String email;
    private LocalDate expirationDate;
    private LocalDate creationDate;
    private String password;

    /**
     * Account's constructor
     * User this constructor when user create a new account
     * @param appName
     * @param email
     * @param username
     * @param password
     */
    public Account(String appName, String email, String username, String password) {
        //generate a unique ID when object is created
        this.accountId = UUID.randomUUID().toString();
        this.appName = appName;
        this.username = username;
        this.email = email;
        this.creationDate = LocalDate.now();
        this.expirationDate = this.creationDate.plusMonths(3);
        this.password = password;
    }

    /**
     * Account's constructor
     * User this constructor when we get data from database
     * @param accountId
     * @param appName
     * @param username
     * @param email
     * @param expirationDate
     * @param creationDate
     * @param password
     */

    public Account(String accountId, String appName, String username, String email, LocalDate expirationDate, LocalDate creationDate, String password) {
        this.accountId = accountId;
        this.appName = appName;
        this.username = username;
        this.email = email;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.password = password;
    }

    @Override
    /**
     * Convert Account to String
     */
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", appName='" + appName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", expirationDate=" + expirationDate +
                ", creationDate=" + creationDate +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * return accountId
     * @return
     */
    public String getAccountId() {
        return accountId.toString();
    }

    /**
     * return app's name
     * @return
     */
    public String getAppName() {
        return appName;
    }

    /**
     * return email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * return expiration date
     * @return
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * return creation date
     * @return
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * return password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * return username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * set app's name
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set accountId
     * @param accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * set expiration date
     * @param expirationDate
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * set creation date
     * @param creationDate
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}