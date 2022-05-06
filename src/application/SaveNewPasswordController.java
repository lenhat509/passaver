/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * SaveNewPasswordController class
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

import java.time.LocalDate;


public class SaveNewPasswordController {

    @FXML
    public TextField appNameField;

    @FXML
    public TextField usernameField;

    @FXML
    public TextField emailField;

    @FXML
    public TextField passwordField;

    @FXML
    public TextField months;
    
    @FXML
    public TextField creationDateField;

    @FXML
    public CheckBox capitalLetterCheckBox;

    @FXML
    public CheckBox specialCharacterCheckbox;

    @FXML
    public CheckBox clipboardCheckbox;

    @FXML
    public Label errorLabel;

    @FXML
    public TextField passwordDisplay;

    public int passwordLength = 15;
    public int passwordMinSize = 1;

    private SharedProperty shared = SharedProperty.getSharedProperty();

    /**
     * initialize the page with creation date as today and
     * expiration date as 3 months from today.
     */
    public void initialize() {
        String creationDate = LocalDate.now().toString();
        creationDateField.setText(creationDate);
    }

    /**
     * create a new account
     * @param mouseEvent
     */
    public void saveNewAccount(MouseEvent mouseEvent) {
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
        String encryptedPw = passUtil.encrypt(password);
        
        Account newAccount = new Account(appName, email, username, encryptedPw);
        newAccount.setExpirationDate(newAccount.getCreationDate().plusMonths(monthsInt));
        AccountDao accountDao = shared.getAccountDao();
        User loginUser = shared.getLoginUser();
        accountDao.saveAccount(loginUser.getUserId(), newAccount);
        shared.navigateTo("home-view.fxml");
    }

    /**
     * generate a password and display on UI
     * @param mouseEvent
     */
    public void generatePassword(MouseEvent mouseEvent) {
        StringBuilder sb = shared.generatePassword(capitalLetterCheckBox, specialCharacterCheckbox, passwordLength);
        String generatedPassword = sb.toString();
        passwordDisplay.setText("");
        passwordDisplay.setText(generatedPassword);
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
     * cancel save, go back to home
     * @param mouseEvent
     */
    public void cancelSave(MouseEvent mouseEvent) {
    	shared.navigateTo("home-view.fxml");
    	
    }
}
