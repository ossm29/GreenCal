package com.example.greencal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;

/** Event handler du bouton newPlant */
public class ControlNewPlant implements EventHandler<ActionEvent> {

    private BorderPane mainPane;
    public ControlNewPlant(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public void showNewPlantForm(BorderPane mainPane) {
        // Utilisez une VBox pour le formulaire
        VBox form = new VBox(10);
        form.setAlignment(Pos.CENTER);

        // Champ surnom
        Label surnomLabel = new Label("Surnom:");
        TextField surnom = new TextField();
        surnom.setPromptText("Surnom");
        surnom.setMaxWidth(300);

        // Champ nom scientifique
        Label nomScientifiqueLabel = new Label("Nom scientifique:");
        TextField nomScientifique = new TextField();
        nomScientifique.setPromptText("Nom scientifique");
        nomScientifique.setMaxWidth(300);

        // DatePickers pour les différentes étapes
        Label plantingDateLabel = new Label("Date de plantation:");
        DatePicker plantingDatePicker = new DatePicker();
        Label repottingDateLabel = new Label("Date de rempotage:");
        DatePicker repottingDatePicker = new DatePicker();
        Label wateringDateLabel = new Label("Date d'arrosage:");
        DatePicker wateringDatePicker = new DatePicker();
        Label maintenanceDateLabel = new Label("Date d'entretien/coupe:");
        DatePicker maintenanceDatePicker = new DatePicker();
        Label harvestDateLabel = new Label("Date de récolte:");
        DatePicker harvestDatePicker = new DatePicker();

        // Bouton choisir image
        Button selectImageButton = new Button("Choisir une image");
        Label selectedImageLabel = new Label();
        selectImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
            fileChooser.getExtensionFilters().add(extFilter);
            File selectedFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

            if (selectedFile != null) {
                selectedImageLabel.setText(selectedFile.getAbsolutePath());
            } else {
                selectedImageLabel.setText("Aucun fichier sélectionné");
            }
        });

        // Bouton enregistrer
        Button saveButton = new Button("Enregistrer");
        saveButton.setMaxWidth(300);
        saveButton.setPrefHeight(40);

        // Ajout des champs du formulaire à la VBox
        form.getChildren().addAll(surnomLabel, surnom, nomScientifiqueLabel, nomScientifique,
                plantingDateLabel, plantingDatePicker, repottingDateLabel, repottingDatePicker,
                wateringDateLabel, wateringDatePicker, maintenanceDateLabel, maintenanceDatePicker,
                harvestDateLabel, harvestDatePicker, selectImageButton, selectedImageLabel, saveButton);

        // Balise CSS
        form.getStyleClass().add("form-background");

        // Affichez le formulaire
        mainPane.setCenter(form);
    }



    @Override
    public void handle(ActionEvent actionEvent) {
        showNewPlantForm(mainPane);
    }
}
