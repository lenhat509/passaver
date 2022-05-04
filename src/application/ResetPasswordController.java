/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * ResetPasswordController class
 */
package application;

import database.dao.UserDao;
import database.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ResetPasswordController {

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField confirmPasswordField;

    @FXML
    public TextField answerField;

    @FXML
    public RadioButton firstQuestion;

    @FXML
    public RadioButton secondQuestion;

    @FXML
    public RadioButton thirdQuestion;

    @FXML
    public Label errorLabel;

    @FXML
    public TextField userIdField;

    private SharedProperty shared = SharedProperty.getSharedProperty();
    private String securityQuestion;
    private int passwordMinSize = 8;

    /**
     * Initialize sign up page with the first security question selected
     */
    public void initialize() {
        firstQuestion.setSelected(true);
        securityQuestion = firstQuestion.getText();
    }

    /**
     * Verify UserId, security question and answer
     * If all passes, reset the password to a new one
     * @param mouseEvent
     */
    public void resetPassword(MouseEvent mouseEvent) {
        String userId = userIdField.getText().trim();
        String answer = answerField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPw = confirmPasswordField.getText().trim();
        Boolean isMatched = password.equals(confirmPw);

        if(userId.isEmpty())
        {
            errorLabel.setText("UserId is required");
            return;
        }

        if(answer.isEmpty())
        {
            errorLabel.setText("Answer is required");
            return;
        }

        if(password.length() < passwordMinSize)
        {
            errorLabel.setText("Password must have at least "+passwordMinSize+ " characters");
            return;
        }

        if(!isMatched)
        {
            errorLabel.setText("Passwords do not match");
            return;
        }

        UserDao userDao = shared.getUserDao();
        User user = userDao.search(userId);
        if(user == null)
        {
            errorLabel.setText("This UserId does not exists");
            return;
        }
        if(!securityQuestion.equals(user.getSecurityQuestion()) || !answer.equals(user.getSecurityAnswer()))
        {
            errorLabel.setText("The security question or answer is wrong");
            return;
        }

        userDao.resetPassword(user.getUserId(), password);
        shared.navigateTo("login-view.fxml");
    }
    /**
     * deselect the other two options
     * @param mouseEvent
     */
    @FXML
    public void firstQuestionSelected(MouseEvent mouseEvent) {
        secondQuestion.setSelected(false);
        thirdQuestion.setSelected(false);
        securityQuestion = firstQuestion.getText();
    }
    /**
     * deselect the other two options
     * @param mouseEvent
     */
    @FXML
    public void secondQuestionSelected(MouseEvent mouseEvent) {
        firstQuestion.setSelected(false);
        thirdQuestion.setSelected(false);
        securityQuestion = secondQuestion.getText();
    }
    /**
     * deselect the other two options
     * @param mouseEvent
     */
    @FXML
    public void thirdQuestionSelected(MouseEvent mouseEvent) {
        firstQuestion.setSelected(false);
        secondQuestion.setSelected(false);
        securityQuestion = thirdQuestion.getText();
    }

    /**
     * navigate to login page
     * @param mouseEvent
     */
    public void backToLogin(MouseEvent mouseEvent) {
        shared.navigateTo("login-view.fxml");
    }
}
