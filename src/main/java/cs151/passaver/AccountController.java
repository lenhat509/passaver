package cs151.passaver;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AccountController {
    @FXML
    public Label userName;

    @FXML
    public Label appName;

    @FXML
    public Label email;

    @FXML
    public Button editBtn;

    @FXML
    public Pane accountPane;

    private SharedProperty shared = SharedProperty.getSharedProperty();

    @FXML
    public void editAccount(MouseEvent mouseEvent) {
        String id = accountPane.getAccessibleText();
        shared.setAccountEditedId(id);
        shared.navigateTo("edit-view.fxml");
    }
}
