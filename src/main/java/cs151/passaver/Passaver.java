package cs151.passaver;

import cs151.database.DatabaseConnection;
import cs151.database.dao.UserDao;
import cs151.database.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

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

        // test database connectivity
//        System.out.println("hello");
//        DatabaseConnection dbconnect = DatabaseConnection.getDBConnection();
//        Connection conn = dbconnect.getConnection();
//        UserDao userDb = new UserDao(conn);
//
//        User user = userDb.search("nhatle");
//        System.out.println(user);

    }
}
