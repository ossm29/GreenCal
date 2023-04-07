package com.example.greencal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.calendarfx.view.CalendarView;
import java.time.LocalDate;

public class Calendar extends Application {

    @Override
    public void start(Stage primaryStage) {
        CalendarView calendarView = new CalendarView();
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowDeveloperConsole(false);

        StackPane root = new StackPane();
        root.getChildren().add(calendarView);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Exemple CalendarFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}