package com.example.greencal;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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

        // Vous pouvez ajouter ici d'autres informations sur la plante et les afficher de la manière souhaitée

        getChildren().addAll(surnomLabel, imageView, plantingDateLabel);
    }
}
