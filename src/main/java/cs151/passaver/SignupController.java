package cs151.passaver;

import cs151.database.dao.UserDao;
import cs151.database.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupController {
    @FXML
    public TextField userIdField;

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

    private int passwordMinSize = 8;

    private SharedProperty shared = SharedProperty.getSharedProperty();

    private String securityQuestion;

    public void initialize() {
        firstQuestion.setSelected(true);
        securityQuestion = firstQuestion.getText();
    }

    public void signUp(MouseEvent mouseEvent) {
        String userId = userIdField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPw = confirmPasswordField.getText().trim();
        String securityAnswer = answerField.getText().trim();
        Boolean isMatched = password.equals(confirmPw);

        if(userId.isEmpty())
        {
            errorLabel.setText("UserId is required");
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

        if(securityAnswer.isEmpty())
        {
            errorLabel.setText("Answer is required");
            return;
        }

        UserDao userDao = shared.getUserDao();
        User user = userDao.search(userId);
        if(user != null)
        {
            errorLabel.setText("This UserId already exists");
            return;
        }
        User newUser = new User(userId, password, securityQuestion, securityAnswer);
        userDao.addUser(newUser);
        shared.navigateTo("login-view.fxml");
    }

    public void firstQuestionSelected(MouseEvent mouseEvent) {
        secondQuestion.setSelected(false);
        thirdQuestion.setSelected(false);
        securityQuestion = firstQuestion.getText();
    }

    public void secondQuestionSelected(MouseEvent mouseEvent) {
        firstQuestion.setSelected(false);
        thirdQuestion.setSelected(false);
        securityQuestion = secondQuestion.getText();
    }

    public void thirdQuestionSelected(MouseEvent mouseEvent) {
        firstQuestion.setSelected(false);
        secondQuestion.setSelected(false);
        securityQuestion = thirdQuestion.getText();
    }

    public void backToLogin(MouseEvent mouseEvent) {
        shared.navigateTo("login-view.fxml");
    }
}
