package cs151.passaver;

import cs151.database.dao.UserDao;
import cs151.database.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    private SharedProperty shared = SharedProperty.getSharedProperty();

    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Label errorLabel;
    @FXML
    public void authenticateUser(MouseEvent mouseEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        UserDao userDao = shared.getUserDao();
        User user = userDao.search(username);

        if(user == null)
        {
            errorLabel.setText("This UserId does not exists");
            return;
        }

        if(!user.getPassword().equals(password))
        {
            errorLabel.setText("Incorrect Password");
            return;
        }
        shared.setLoginUser(user);
        shared.navigateTo("home-view.fxml");
    }

    public void goToSignUp(MouseEvent mouseEvent) {
        shared.navigateTo("signup-view.fxml");
    }

    public void goToResetPw(MouseEvent mouseEvent) {
        shared.navigateTo("reset-password-view.fxml");
    }
}