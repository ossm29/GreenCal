module com.example.greencal {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires com.calendarfx.view;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires org.json;

    opens com.example.greencal to javafx.fxml;
    exports com.example.greencal;
    exports com.example.greencal.Controller;
    opens com.example.greencal.Controller to javafx.fxml;
}