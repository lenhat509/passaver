package cs151.database.models;

import java.time.LocalDate;
import java.util.UUID;

//Model Object of Account
public class Account {

    //UUID is a library to generate a unique id
    private String accountId;
    private String appName;
    private String username;
    private String email;
    private LocalDate expirationDate;
    private LocalDate creationDate;
    private String password;

    //Call this constructor when user create a new account
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


    //Call this constructor when we get data from database
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

    public String getAccountId() {
        return accountId.toString();
    }

    public String getAppName() {
        return appName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setPassword(String password) {
        this.password = password;
        LocalDate date = LocalDate.now();
        setCreationDate(date);
        setExpirationDate(date.plusMonths(3));
    }
}