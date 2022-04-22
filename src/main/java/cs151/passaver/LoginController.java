package cs151.passaver;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onSubmitClick() {
        welcomeText.setText("Login");
    }
}