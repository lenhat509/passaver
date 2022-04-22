module cs151.passaver {
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires javafx.controls;
    requires javafx.fxml;


    opens cs151.passaver to javafx.fxml;
    exports cs151.passaver;
}