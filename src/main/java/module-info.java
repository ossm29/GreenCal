module com.example.greencal {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires com.calendarfx.view;

    opens com.example.greencal to javafx.fxml;
    exports com.example.greencal;
}