/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * MainController class
 */
package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MainController {

    @FXML
    public AnchorPane anchorPane;

    /**
     * Load the login-view, then insert it into main-view
     */
    @FXML
    public void initialize() {
        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("login-view.fxml"));
            anchorPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
