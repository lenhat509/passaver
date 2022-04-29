package cs151.passaver;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AccountController {
    @FXML
    public Label userName;

    @FXML
    public Label appName;

    @FXML
    public Label email;

    @FXML
    public void editAccount(MouseEvent mouseEvent) {
        System.out.println(userName.getText() + " " + appName.getText() + " " + email.getText());
    }
}
