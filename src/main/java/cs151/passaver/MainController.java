package cs151.passaver;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MainController {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader(Passaver.class.getResource("login-view.fxml"));
        try {
            AnchorPane pane = (AnchorPane) fxmlLoader.load();
            anchorPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
