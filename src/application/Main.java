/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * Main class
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private SharedProperty shared = SharedProperty.getSharedProperty();

    /**
     * start method
     * load main-view
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("main-view.fxml"));
        shared.setMainPane(pane);
        Scene scene = new Scene(pane, 800, 600);
        stage.setTitle("Passaver");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}