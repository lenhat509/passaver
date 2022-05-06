/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * LoginController class
 */

package application;

import database.dao.UserDao;
import database.models.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
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

    /**
     * login a user, check if userId and password exist and match
     * @param mouseEvent
     */
    @FXML
    public void validate(MouseEvent mouseEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        UserDao userDao = shared.getUserDao();
        User user = userDao.search(username);
        
        if(user == null)
        {
            errorLabel.setText("This UserId does not exists");
            return;
        }
        
        PassUtil passUtil = new PassUtil();
        String decryptedPass = passUtil.decrypt(user.getPassword());
        if(!decryptedPass.equals(password))
        {
        	
            errorLabel.setText("Incorrect Password");
            return;
        }
        shared.setLoginUser(user);
        shared.navigateTo("reminder-view.fxml");
    }

    /**
     * navigate to sign up page
     * @param mouseEvent
     */
    public void goToSignUp(MouseEvent mouseEvent) {
        shared.navigateTo("signup-view.fxml");
    }

    /**
     * navigate to reset password page
     * @param mouseEvent
     */
    public void goToResetPw(MouseEvent mouseEvent) {
        shared.navigateTo("reset-password-view.fxml");
    }
}