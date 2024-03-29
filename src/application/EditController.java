/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * EditController class
 */
package application;

import database.dao.AccountDao;
import database.models.Account;
import database.models.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class EditController {
    @FXML
    public AnchorPane editPane;

    @FXML
    public TextField appNameField;

    @FXML
    public TextField usernameField;

    @FXML
    public TextField emailField;

    @FXML
    public TextField passwordField;

    @FXML
    public TextField creationDateField;

    @FXML
    public TextField specialCharacters;
    
    @FXML
    public TextField capitalLetters;
    
    @FXML
    public TextField minLength;
    
    @FXML
    public TextField maxLength;
    @FXML
    public CheckBox clipboardCheckbox;

    @FXML
    public Label errorLabel;
    
    @FXML
    public TextField months;

    @FXML
    public TextField passwordDisplay;

    public int passwordMinSize = 1;

    private SharedProperty shared = SharedProperty.getSharedProperty();
    private Account account;

    /**
     * Initialize the edit page with the information that
     * the associated Account object contain.
     */
    public void initialize() {
        String accountId = shared.getAccountEditedId();
        AccountDao accountDao = shared.getAccountDao();
        Account account = accountDao.getAccount(accountId);
        setAccount(account);
        appNameField.setText(account.getAppName());
        usernameField.setText(account.getUsername());
        emailField.setText(account.getEmail());
        PassUtil passUtil = new PassUtil();
        String decryptedPw = passUtil.decrypt(account.getPassword());
        passwordField.setText(decryptedPw);
        String creationDate = LocalDate.now().toString();
        creationDateField.setText(creationDate);
        minLength.setText("10");
        maxLength.setText("20");
        capitalLetters.setText("0");
        specialCharacters.setText("0");
        
    }

    /**
     * Update the Account object with the changes the user makes
     * @param mouseEvent
     */
    public void updateAccount(MouseEvent mouseEvent) {
        String appName = appNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String numberOfMonths = months.getText().trim();
        
        if(appName.isEmpty())
        {
            errorLabel.setText("App Name is required");
            return;
        }

        if(username.isEmpty() && email.isEmpty())
        {
            errorLabel.setText("Username or Email is required");
            return;
        }
        
        if(numberOfMonths.isEmpty())
        {
        	errorLabel.setText("Please enter lifetime of this account");
        	return;
        }
        
        int monthsInt = 0;
        try {
        	monthsInt = Integer.parseInt(numberOfMonths);
        }
        catch(Exception ex) {
        	errorLabel.setText("Invalid number of months");
        	return;
        }

        if(password.length() < passwordMinSize)
        {
            errorLabel.setText("Password must have at least "+passwordMinSize+ " characters");
            return;
        }
        PassUtil passUtil = new PassUtil();
        String encryptedPw = passUtil.encrypt(passwordField.getText().trim());
        account.setPassword(encryptedPw);
        account.setAppName(appNameField.getText().trim());
        account.setUsername(usernameField.getText().trim());
        account.setEmail(emailField.getText().trim());
        account.setCreationDate(LocalDate.parse(creationDateField.getText()));
        account.setExpirationDate(LocalDate.parse(creationDateField.getText()).plusMonths(monthsInt));
        AccountDao accountDao = shared.getAccountDao();
        accountDao.editAccount(account);
        shared.navigateTo("home-view.fxml");
    }

    /**
     * generate a password and display on UI
     * @param mouseEvent
     */
    public void generatePassword(MouseEvent mouseEvent) {
    	try {
    		int min = Integer.parseInt(minLength.getText());
        	int max = Integer.parseInt(maxLength.getText());
        	int capital = Integer.parseInt(capitalLetters.getText());
        	int special = Integer.parseInt(specialCharacters.getText());
        	if(min < 0 || max <= 0 || max < min || capital < 0 || special < 0) throw new Exception();
            StringBuilder sb = shared.generatePassword(capital, special, min, max);
            String generatedPassword = sb.toString();
            passwordDisplay.setText("");
            passwordDisplay.setText(generatedPassword);
    	}
    	catch(Exception e) {}
    }

    /**
     * copy generated password to clipboard
     * @param mouseEvent
     */
    public void copyToClipBoard(MouseEvent mouseEvent) {
        if(clipboardCheckbox.isSelected())
        {
            ClipboardContent cc = new ClipboardContent();
            cc.putString(passwordDisplay.getText());
            Clipboard.getSystemClipboard().setContent(cc);
        }
    }

    /**
     * cancel update and go back to home page
     * @param mouseEvent
     */
    public void cancelUpdate(MouseEvent mouseEvent) {
        shared.navigateTo("home-view.fxml");
    }

    /**
     * set the associated account
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
