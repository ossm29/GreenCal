package com.example.greencal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PlantPage extends VBox {

    public PlantPage(Plant plant) {
        setSpacing(10);
        setPadding(new Insets(10));

        // Affiche le surnom de la plante
        Label surnomLabel = new Label("Surnom: " + plant.getSurnom());
        surnomLabel.getStyleClass().add("title2-label");

        // Affiche l'image de la plante
        ImageView imageView = new ImageView(new Image("file:" + plant.getImagePath(), 150, 150, true, true));

        // Affiche la date de plantation
        Label plantingDateLabel = new Label("Date de plantation: " + plant.getPlantationDate().toString());

        // Crée les listes pour les dates de rempotage, arrosage, entretien et récolte
        HBox listsContainer = new HBox(10);

        VBox rempotageList = createListWithHeaderAndButton("Dates de rempotage", plant.getRempotageDates(), () -> {
            LocalDate date = showDatePickerDialog();
            if (date != null) {
                plant.addRempotageDate(date);
            }
        });
        VBox arrosageList = createListWithHeaderAndButton("Dates d'arrosage", plant.getArrosageDates(), () -> {
            LocalDate date = showDatePickerDialog();
            if (date != null) {
                plant.addArrosageDate(date);
            }
        });
        VBox entretienList = createListWithHeaderAndButton("Dates d'entretien", plant.getEntretienDates(), () -> {
            LocalDate date = showDatePickerDialog();
            if (date != null) {
                plant.addEntretienDate(date);
            }
        });
        VBox recolteList = createListWithHeaderAndButton("Dates de récolte", plant.getRecolteDates(), () -> {
            LocalDate date = showDatePickerDialog();
            if (date != null) {
                plant.addRecolteDate(date);
            }
        });

        listsContainer.getChildren().addAll(rempotageList, arrosageList, entretienList, recolteList);

        getChildren().addAll(surnomLabel, imageView, plantingDateLabel, listsContainer);
    }

    private VBox createListWithHeaderAndButton(String title, ArrayList<LocalDate> dates, Runnable onButtonClick) {
        Label header = new Label(title);
        ListView<LocalDate> listView = new ListView<>();
        listView.getItems().addAll(dates);

        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            onButtonClick.run();
            listView.getItems().clear();
            listView.getItems().addAll(dates);
        });

        VBox container = new VBox(5, header, listView, addButton);

        return container;
    }

    private LocalDate showDatePickerDialog() {
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle("Choisir une date");
        dialog.setHeaderText("Sélectionnez une date:");

        DatePicker datePicker = new DatePicker();

        dialog.getDialogPane().setContent(datePicker);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                return datePicker.getValue();
            }
            return null;
        });

        Optional<LocalDate> result = dialog.showAndWait();
        return result.orElse(null);
    }

}