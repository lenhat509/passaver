package cs151.database.models;

import java.util.Date;
import java.util.UUID;

//Model Object of Account
public class Account {

    //UUID is a library to generate a unique id
    private UUID accountId;
    private String appName;
    private String email;
    private Date expirationDate;
    private Date creationDate;
    private String password;

    //Call this constructor when user create a new account
    public Account(String appName, String email, Date expirationDate, Date creationDate, String password) {
        //generate a unique ID when object is created
        this.accountId = UUID.randomUUID();
        this.appName = appName;
        this.email = email;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.password = password;
    }
    //Call this constructor when we get data from database
    public Account(UUID accountId, String appName, String email, Date expirationDate, Date creationDate, String password) {
        this.accountId = accountId;
        this.appName = appName;
        this.email = email;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.password = password;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getAppName() {
        return appName;
    }

    public String getEmail() {
        return email;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}