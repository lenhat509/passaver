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
     * @param minLength
     * @param maxLength
     * @return
     */
    public StringBuilder generatePassword(int capital, int special, int minLength, int maxLength) { 
        StringBuilder password = new StringBuilder();
        
        if(capital + special <= maxLength) {
        	String numberList = "0123456789";
            String lowercaseList = "abcdefghijklmnopqrstuvwxyz";
            String uppercaseList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String specialCharacterList = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
            
            Random random = new Random();
            
            int normalCharMax = maxLength - (capital + special);
            int normalCharMin = minLength >= (capital + special) ? minLength - (capital + special) : 0;
            int normalChar = normalCharMin + random.nextInt(normalCharMax - normalCharMin + 1);
            
            ArrayList<String> numberAndAlphabet = new ArrayList<String>();
            numberAndAlphabet.add(numberList);
            numberAndAlphabet.add(lowercaseList);
            
            for(int i = 0; i < normalChar; i++) {
            	int type = random.nextInt(numberAndAlphabet.size());
            	String list = numberAndAlphabet.get(type);
            	int index = random.nextInt(list.length());
            	password.append(list.charAt(index));
            }
            
            for(int i = 0; i < special; i ++) {
            	int index = random.nextInt(password.length()+1);
            	char specialChar = specialCharacterList.charAt(random.nextInt(specialCharacterList.length()));
            	password.insert(index, specialChar);
            }
            
            for(int i = 0; i < capital; i ++) {
            	int index = random.nextInt(password.length()+1);
            	char uppercaseLetter = uppercaseList.charAt(random.nextInt(uppercaseList.length()));
            	password.insert(index, uppercaseLetter);
            }   
        }
        
        return password;
    }

}
