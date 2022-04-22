package cs151.passaver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Passaver extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Passaver.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Passaver");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}