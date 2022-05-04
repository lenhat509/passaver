/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * SharedProperty class
 */
package application;

import database.DatabaseConnection;
import database.dao.AccountDao;
import database.dao.UserDao;
import database.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//Singleton class
public class SharedProperty {
    private static SharedProperty sharedProperty = new SharedProperty();
    private static DatabaseConnection db;
    private UserDao userDao;
    private AccountDao accountDao;
    private AnchorPane mainPane;
    private User loginUser;
    private String accountEditedId;;


    /**
     * SharedProperty's constructor
     */
    private SharedProperty() {
        db = DatabaseConnection.getDBConnection();
        userDao = new UserDao(db.getConnection());
        accountDao = new AccountDao(db.getConnection());
    }

    /**
     * return SharedProperty reference
     * @return
     */
    public static SharedProperty getSharedProperty() {
        return sharedProperty;
    }

    /**
     * return UserDao object
     * @return
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * return AccountDao object
     * @return
     */
    public AccountDao getAccountDao() {
        return accountDao;
    }

    /**
     * set the main Pane of main-view
     * @param pane
     */
    public void setMainPane(AnchorPane pane) {
        mainPane = pane;
    }

    /**
     * get login user
     * @return
     */
    public User getLoginUser() {
        return loginUser;
    }

    /**
     * set login user
     * @param loginUser
     */
    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * get Account object of Edit Account page
     * @return
     */
    public String getAccountEditedId() {
        return accountEditedId;
    }

    /**
     * set Account object of Edit Account page
     * @param accountEditedId
     */
    public void setAccountEditedId(String accountEditedId) {
        this.accountEditedId = accountEditedId;
    }

    /**
     * navigate to other page
     * @param fmxlFile
     */
    public void navigateTo(String fmxlFile)
    {
        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource(fmxlFile));
            if(mainPane.getChildren().size() > 0) {
                mainPane.getChildren().remove(0);
                mainPane.getChildren().add(pane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * generate a random password
     * @param capital
     * @param special
     * @param passwordLength
     * @return
     */
    public StringBuilder generatePassword(CheckBox capital, CheckBox special, int passwordLength) {
        String numberList = "0123456789";
        String lowercaseList = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacterList = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        ArrayList<String> characterTypes = new ArrayList<>();
        characterTypes.add(numberList);
        characterTypes.add(lowercaseList);

        Boolean isCapital = capital.isSelected();
        Boolean isSpecial = special.isSelected();

        if(isCapital) characterTypes.add(uppercaseList);
        if(isSpecial) characterTypes.add(specialCharacterList);

        Random typeRandom = new Random();
        Random characterRandom = new Random();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < passwordLength; i++) {
            int typeIndex = typeRandom.nextInt(characterTypes.size());
            String type = characterTypes.get(typeIndex);
            int charIndex = characterRandom.nextInt(type.length());
            sb.append(type.charAt(charIndex));
        }

        return sb;
    }

}
