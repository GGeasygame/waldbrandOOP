module ch.ictbz.waldbrandoop.waldbrandoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens ch.ictbz.waldbrandoop.waldbrandoop to javafx.fxml;
    exports ch.ictbz.waldbrandoop.waldbrandoop;
}