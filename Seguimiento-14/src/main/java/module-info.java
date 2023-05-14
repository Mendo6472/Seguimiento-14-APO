module seguimiento.seguimiento14 {
    requires javafx.controls;
    requires javafx.fxml;


    opens seguimiento.seguimiento14 to javafx.fxml;
    exports seguimiento.seguimiento14;
    exports model;
    opens model to javafx.fxml;
}