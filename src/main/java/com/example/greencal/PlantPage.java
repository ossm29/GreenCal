package com.example.greencal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlantPage extends ScrollPane {

    public PlantPage(Plant plant) {
        VBox content = new VBox();
        content.setSpacing(10);
        content.setPadding(new Insets(10));

        // Affiche le surnom de la plante
        Label surnomLabel = new Label("Ma " + plant.getSurnom());
        surnomLabel.getStyleClass().add("title2-label");

        // Affiche l'image de la plante
        ImageView imageView = new ImageView(new Image("file:" + plant.getImagePath(), 150, 150, true, true));

        // Affiche la date de plantation
        Label plantingDateLabel = new Label("Date de plantation: " + plant.getPlantationDate().toString());

        // Crée les listes pour les dates de rempotage, arrosage, entretien et récolte
        HBox listsContainer = new HBox(10);

        VBox rempotageList = createListWithHeaderAndButton("Dates de rempotage", plant.getRempotageDates(), () -> {
            LocalDate date = showDatePickerDialog("rempotage");
            if (date != null) {
                plant.addRempotageDate(date);
            }
        });
        VBox arrosageList = createListWithHeaderAndButton("Dates d'arrosage", plant.getArrosageDates(), () -> {
            LocalDate date = showDatePickerDialog("arrosage");
            if (date != null) {
                plant.addArrosageDate(date);
            }
        });
        VBox entretienList = createListWithHeaderAndButton("Dates d'entretien", plant.getEntretienDates(), () -> {
            LocalDate date = showDatePickerDialog("entretien");
            if (date != null) {
                plant.addEntretienDate(date);
            }
        });
        VBox recolteList = createListWithHeaderAndButton("Dates de récolte", plant.getRecolteDates(), () -> {
            LocalDate date = showDatePickerDialog("récolte");
            if (date != null) {
                plant.addRecolteDate(date);
            }
        });

        listsContainer.getChildren().addAll(rempotageList, arrosageList, entretienList, recolteList);

        content.getChildren().addAll(surnomLabel, imageView, plantingDateLabel, listsContainer);
        content.getStyleClass().add("plants-background");

        // Affiche le LineChart des mesures de taille de la plante avec le bouton d'ajout
        VBox plantSizeChartWithButton = createPlantSizeChartWithButton(plant.getSizeMeasurements(), plant);
        content.getChildren().add(plantSizeChartWithButton);

        // Affiche la liste des notes et le bouton d'ajout
        VBox notesListWithButton = createNotesListWithButton(plant.getNotes(), plant);
        content.getChildren().add(notesListWithButton);

        // Définir le contenu du ScrollPane
        this.setContent(content);
    }

    private VBox createListWithHeaderAndButton(String title, ArrayList<LocalDate> dates, Runnable onButtonClick) {
        Label header = new Label(title);
        ListView<LocalDate> listView = new ListView<>();
        listView.setPrefHeight(100); // Modifiez cette valeur pour ajuster la hauteur des tableaux
        dates.stream().filter(Objects::nonNull).forEach(listView.getItems()::add);

        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            onButtonClick.run();
            listView.getItems().clear();
            dates.stream().filter(Objects::nonNull).forEach(listView.getItems()::add);
        });

        HBox headerContainer = new HBox(header, addButton);
        headerContainer.setSpacing(10); // Ajustez l'espacement entre le titre et le bouton
        headerContainer.setAlignment(Pos.CENTER_LEFT); // Alignez le titre et le bouton à gauche
        HBox.setHgrow(header, Priority.ALWAYS); // Assurez-vous que le titre prend tout l'espace disponible, laissant le bouton à droite

        VBox container = new VBox(5, headerContainer, listView);

        return container;
    }


    private LocalDate showDatePickerDialog(String dateType) {
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle("+ "+dateType);
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

    private VBox createPlantSizeChartWithButton(List<PlantSizeMeasurement> measurements, Plant plant) {
        LineChart<String, Number> lineChart = createPlantSizeChart(measurements);

        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            PlantSizeMeasurement newMeasurement = showAddMeasurementDialog();
            if (newMeasurement != null) {
                plant.addSizeMeasurement(newMeasurement);
                lineChart.getData().clear();
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                for (PlantSizeMeasurement measurement : plant.getSizeMeasurements()) {
                    String dateAsString = measurement.getDate().format(formatter);
                    series.getData().add(new XYChart.Data<>(dateAsString, measurement.getSizeInCm()));
                }

                lineChart.getData().add(series);
            }
        });

        HBox chartTitleContainer = new HBox();
        chartTitleContainer.setAlignment(Pos.CENTER);
        chartTitleContainer.setSpacing(10);
        Label chartTitle = new Label("Évolution de la taille de la plante");
        chartTitleContainer.getChildren().addAll(chartTitle, addButton);

        VBox container = new VBox(5, chartTitleContainer, lineChart);

        return container;
    }


    private LineChart<String, Number> createPlantSizeChart(List<PlantSizeMeasurement> measurements) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Taille (cm)");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        //lineChart.setTitle("Évolution de la taille de la plante");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Taille");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (PlantSizeMeasurement measurement : measurements) {
            String dateAsString = measurement.getDate().format(formatter);
            series.getData().add(new XYChart.Data<>(dateAsString, measurement.getSizeInCm()));
        }

        lineChart.getData().add(series);

        return lineChart;
    }

    private PlantSizeMeasurement showAddMeasurementDialog() {
        Dialog<PlantSizeMeasurement> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une mesure");
        dialog.setHeaderText("Saisissez la taille et la date de la mesure:");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField sizeTextField = new TextField();
        sizeTextField.setPromptText("Taille en cm");
        DatePicker datePicker = new DatePicker();

        grid.add(new Label("Taille:"), 0, 0);
        grid.add(sizeTextField, 1, 0);
        grid.add(new Label("Date:"), 0, 1);
        grid.add(datePicker, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                double size = Double.parseDouble(sizeTextField.getText());
                LocalDate date = datePicker.getValue();
                return new PlantSizeMeasurement(size, date);
            }
            return null;
        });

        Optional<PlantSizeMeasurement> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private String showAddNoteDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter une note");
        dialog.setHeaderText("Saisissez votre note:");
        dialog.setContentText("Note:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }


    private VBox createNotesListWithButton(ArrayList<String> notes, Plant plant) {
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(notes);

        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            String newNote = showAddNoteDialog();
            if (newNote != null) {
                plant.addNote(newNote);
                listView.getItems().add(newNote);
            }
        });

        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setSpacing(10);
        Label header = new Label("Notes");
        headerContainer.getChildren().addAll(header, addButton);

        VBox container = new VBox(5, headerContainer, listView);
        return container;
    }




}