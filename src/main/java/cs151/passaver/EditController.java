package cs151.passaver;

import cs151.database.dao.AccountDao;
import cs151.database.models.Account;
import cs151.database.models.User;
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
    public TextField confirmPasswordField;

    @FXML
    public TextField creationDateField;

    @FXML
    public TextField expirationDateField;

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
    public int passwordMinSize = 8;

    private SharedProperty shared = SharedProperty.getSharedProperty();
    private Account account;



    public void initialize() {
        String accountId = shared.getAccountEditedId();
        AccountDao accountDao = shared.getAccountDao();
        Account account = accountDao.getAccount(accountId);
        setAccount(account);
        appNameField.setText(account.getAppName());
        usernameField.setText(account.getUsername());
        emailField.setText(account.getEmail());
        String creationDate = LocalDate.now().toString();
        String expirationDate = LocalDate.now().plusMonths(3).toString();
        creationDateField.setText(creationDate);
        expirationDateField.setText(expirationDate);

    }
    public void updateAccount(MouseEvent mouseEvent) {
        String appName = appNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPw = confirmPasswordField.getText().trim();
        Boolean isMatched = password.equals(confirmPw);



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

        if(password.length() < passwordMinSize && password.length() != 0)
        {
            errorLabel.setText("Password must have at least "+passwordMinSize+ " characters");
            return;
        }

        if(!isMatched)
        {
            errorLabel.setText("Passwords do not match");
            return;
        }

        if(password.length() != 0)
            account.setPassword(passwordField.getText());
        account.setAppName(appNameField.getText());
        account.setUsername(usernameField.getText());
        account.setEmail(emailField.getText());
        account.setCreationDate(LocalDate.parse(creationDateField.getText()));
        account.setExpirationDate(LocalDate.parse(expirationDateField.getText()));
        AccountDao accountDao = shared.getAccountDao();
        accountDao.editAccount(account);
        shared.navigateTo("home-view.fxml");
    }

    public void generatePassword(MouseEvent mouseEvent) {
        String numberList = "0123456789";
        String lowercaseList = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacterList = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        ArrayList<String> characterTypes = new ArrayList<>();
        characterTypes.add(numberList);
        characterTypes.add(lowercaseList);

        Boolean isCapital = capitalLetterCheckBox.isSelected();
        Boolean isSpecial = specialCharacterCheckbox.isSelected();

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

        String generatedPassword = sb.toString();

        passwordDisplay.setText("");
        passwordDisplay.setText(generatedPassword);



    }

    public void copyToClipBoard(MouseEvent mouseEvent) {
        if(clipboardCheckbox.isSelected())
        {
            ClipboardContent cc = new ClipboardContent();
            cc.putString(passwordDisplay.getText());
            Clipboard.getSystemClipboard().setContent(cc);
        }
    }

    public void cancelUpdate(MouseEvent mouseEvent) {
        shared.navigateTo("home-view.fxml");
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
