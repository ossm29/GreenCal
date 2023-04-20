package com.example.greencal;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.TimeField;
import com.example.greencal.Controller.ControlMyPlants;
import com.example.greencal.Controller.ControlNewPlant;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.json.JSONObject;



import javafx.stage.Stage;
import com.calendarfx.view.CalendarView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class GreenCal extends Application {

    @Override
    public void start(Stage primaryStage) {
        CalendarView calendarView = new CalendarView();
        Calendar calendar = new Calendar();
        CalendarSource calendarSource = new CalendarSource();
        calendarSource.getCalendars().addAll(calendar);
        calendarView.getCalendarSources().add(calendarSource);
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowDeveloperConsole(false);

        Button homeButton = createIconButton("/images/home.png","Accueil");
        Button newEventButton = createIconButton("/images/calendar-plus.png","Nouvel évènement");
        Button myPlantsButton = createIconButton("/images/plant.png","Consulter les plantes");
        Button newPlantButton = createIconButton("/images/plant-plus.png","Nouvelle plante");
        Button helpButton = createIconButton("/images/help.png","Aide");

        VBox menu = new VBox(10, homeButton, newEventButton, myPlantsButton, newPlantButton, helpButton);
        menu.setAlignment(Pos.TOP_LEFT);
        menu.setPadding(new Insets(10));
        menu.getStyleClass().add("menu");

        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(menu);
        mainPane.setCenter(calendarView);

        // On ajoute la topBar
        mainPane.setTop(new WeatherBox());

        /* Handler des boutons */
        homeButton.setOnAction(event -> mainPane.setCenter(calendarView));
        newEventButton.setOnAction(event -> showAddEventDialog(calendarView,calendar));
        newPlantButton.setOnAction(new ControlNewPlant(mainPane));
        myPlantsButton.setOnAction(new ControlMyPlants(mainPane));
        helpButton.setOnAction(event -> {
            // Créer un Dialog pour afficher l'aide
            Dialog<Void> helpDialog = new Dialog<>();
            helpDialog.setTitle("Aide");

            // Charger l'image d'aide
            Image helpImage = new Image(getClass().getResource("/images/aide.png").toExternalForm());
            ImageView helpImageView = new ImageView(helpImage);

            // Définir le contenu du Dialog
            helpDialog.getDialogPane().setContent(helpImageView);
            helpDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            helpDialog.setResizable(true);

            // Afficher le Dialog
            helpDialog.showAndWait();
        });

        Scene scene = new Scene(mainPane, 1080, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        primaryStage.setTitle("GreenCal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private Button createIconButton(String imagePath, String tooltipText) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);

        Button button = new Button();
        button.setGraphic(imageView);
        button.getStyleClass().add("menu-button");

        Tooltip tooltip = new Tooltip(tooltipText);
        Tooltip.install(button, tooltip);

        return button;
    }

    private void showAddEventDialog(CalendarView calendarView, Calendar cal) {
        Dialog<Entry<String>> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un événement");
        dialog.setHeaderText("Créez un nouvel événement:");

        ButtonType createButtonType = new ButtonType("Créer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField eventName = new TextField();
        eventName.setPromptText("Nom de l'événement");

        DatePicker eventDate = new DatePicker();
        eventDate.setPromptText("Date de l'événement");

        TimeField startTime = new TimeField();
        TimeField endTime = new TimeField();

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(eventName, 1, 0);
        grid.add(new Label("Date:"), 0, 1);
        grid.add(eventDate, 1, 1);
        grid.add(new Label("Heure de début:"), 0, 2);
        grid.add(startTime, 1, 2);
        grid.add(new Label("Heure de fin:"), 0, 3);
        grid.add(endTime, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                LocalDate selectedDate = eventDate.getValue();
                LocalTime startDateTime = startTime.getValue();
                LocalTime endDateTime = endTime.getValue();

                // Créez les objets LocalDateTime en combinant la date et l'heure sélectionnées
                LocalDateTime startLocalDateTime = LocalDateTime.of(selectedDate, startDateTime);
                LocalDateTime endLocalDateTime = LocalDateTime.of(selectedDate, endDateTime);

                Entry<String> entry = new Entry<>(eventName.getText());
                entry.setInterval(startLocalDateTime, endLocalDateTime);

                return entry;
            }
            return null;
        });

        Optional<Entry<String>> result = dialog.showAndWait();

        result.ifPresent(entry -> {
            // Ajoutez l'événement au calendrier
            cal.addEntry(entry);
        });
    }




}