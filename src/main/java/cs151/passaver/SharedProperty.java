package cs151.passaver;

import cs151.database.DatabaseConnection;
import cs151.database.dao.AccountDao;
import cs151.database.dao.UserDao;
import cs151.database.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SharedProperty {
    private static SharedProperty sharedProperty = new SharedProperty();
    private static DatabaseConnection db;
    private UserDao userDao;
    private AccountDao accountDao;
    private AnchorPane mainPane;
    private User loginUser;

    private SharedProperty() {
        db = DatabaseConnection.getDBConnection();
        userDao = new UserDao(db.getConnection());
        accountDao = new AccountDao(db.getConnection());
    }

    public static SharedProperty getSharedProperty() {
        return sharedProperty;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setMainPane(AnchorPane pane) {
        mainPane = pane;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public void navigateTo(String fmxlFile)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Passaver.class.getResource(fmxlFile));
        try {
            AnchorPane pane = (AnchorPane) fxmlLoader.load();
            if(mainPane.getChildren().size() > 0) {
                mainPane.getChildren().remove(0);
                mainPane.getChildren().add(pane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
