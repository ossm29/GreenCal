package com.example.greencal;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import com.calendarfx.view.CalendarView;

import java.util.Objects;

public class GreenCal extends Application {

    @Override
    public void start(Stage primaryStage) {
        CalendarView calendarView = new CalendarView();
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowDeveloperConsole(false);

        Button homeButton = createIconButton("/images/home.png");
        Button newEventButton = createIconButton("/images/calendar-plus.png");
        Button myPlantsButton = createIconButton("/images/plant.png");
        Button newPlantButton = createIconButton("/images/plant-plus.png");
        Button helpButton = createIconButton("/images/help.png");

        VBox menu = new VBox(10, homeButton, newEventButton, myPlantsButton, newPlantButton, helpButton);
        menu.setAlignment(Pos.TOP_LEFT);
        menu.setPadding(new Insets(10));
        menu.getStyleClass().add("menu");

        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(menu);
        mainPane.setCenter(calendarView);

        Scene scene = new Scene(mainPane, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        primaryStage.setTitle("GreenCal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private Button createIconButton(String imagePath) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);

        Button button = new Button();
        button.setGraphic(imageView);
        button.getStyleClass().add("menu-button");

        return button;
    }


}