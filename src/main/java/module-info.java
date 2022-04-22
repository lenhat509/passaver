module cs151.passaver {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs151.passaver to javafx.fxml;
    exports cs151.passaver;
}