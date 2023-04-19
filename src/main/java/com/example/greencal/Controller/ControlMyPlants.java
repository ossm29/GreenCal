package com.example.greencal.Controller;

import com.example.greencal.Plant;
import com.example.greencal.PlantPage;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Callback;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/** Contrôleur associé au bouton myPlants permettant de consulter la liste des plantes */
public class ControlMyPlants implements EventHandler<ActionEvent> {

    private BorderPane mainPane;

    public ControlMyPlants(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        showMyPlants(mainPane);
    }

    /**
     * Affiche la liste des plantes de l'utilisateur avec leurs informations principales
     * dans un tableau. Le tableau est centré sur l'écran et comprend un titre "Mes Plantes"
     * ainsi qu'un bouton "Supprimer" pour supprimer une plante sélectionnée.
     * Un double-clic sur une plante du tableau permet d'afficher les détails de la plante
     * et de planifier des événements/tâches spécifiques.
     *
     * @param mainPane Le BorderPane principal de l'application sur lequel le contenu
     *                 de la méthode sera ajouté.
     */
    public void showMyPlants(BorderPane mainPane) {
        // Titre "Mes Plantes"
        Label titleLabel = new Label("Mes Plantes");
        titleLabel.getStyleClass().add("title2-label"); // Balise CSS

        // Table des plantes
        TableView<Plant> plantsTable = new TableView<>();

        // largeur fixe pour le tableau
        plantsTable.setMaxWidth(900);

        TableColumn<Plant, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));

        TableColumn<Plant, ImageView> imageColumn = new TableColumn<>("Image");
        imageColumn.setCellValueFactory(param -> {
            ImageView imageView = new ImageView(new Image("file:" + param.getValue().getImagePath(), 50, 50, true, true));
            return new ReadOnlyObjectWrapper<>(imageView);
        });

        TableColumn<Plant, String> surnomColumn = new TableColumn<>("Surnom");
        surnomColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getSurnom()));

        TableColumn<Plant, LocalDate> plantingDateColumn = new TableColumn<>("Date de plantation");
        plantingDateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getPlantationDate()));

        // Ajoutez la colonne "Notes"
        TableColumn<Plant, String> notesColumn = new TableColumn<>("Notes");
        notesColumn.setCellValueFactory(param -> {
            List<String> notes = param.getValue().getNotes();
            if (notes != null && !notes.isEmpty()) {
                return new ReadOnlyObjectWrapper<>(notes.get(notes.size() - 1));
            } else {
                return new ReadOnlyObjectWrapper<>("");
            }
        });

        // Configurer la colonne "Notes" pour qu'elle prenne toute la place restante à droite du tableau
        notesColumn.prefWidthProperty().bind(plantsTable.widthProperty().subtract(idColumn.widthProperty()).subtract(imageColumn.widthProperty()).subtract(surnomColumn.widthProperty()).subtract(plantingDateColumn.widthProperty()));
        notesColumn.setMaxWidth(Double.MAX_VALUE);

        // Ajouter la colonne "Notes" à la liste des colonnes du tableau
        plantsTable.getColumns().addAll(idColumn, imageColumn, surnomColumn, plantingDateColumn, notesColumn);

        plantsTable.setItems(loadPlants());

        // StackPane pour centrer horizontalement le tableau
        StackPane tableContainer = new StackPane(plantsTable);
        tableContainer.setAlignment(Pos.CENTER);

        // Bouton "Supprimer"
        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(event -> {
            Plant selectedPlant = plantsTable.getSelectionModel().getSelectedItem();
            if (selectedPlant != null) {
                deletePlant(selectedPlant, plantsTable);
            }
        });



        // Ajouter un événement pour afficher les détails de la plante et planifier des événements/tâches spécifiques
        plantsTable.setRowFactory(tv -> {
            TableRow<Plant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Plant rowData = row.getItem();
                    // Afficher les détails de la plante
                    showPlantDetails(mainPane, rowData);
                }
            });
            return row;
        });

        // HBox pour contenir le titre et le bouton "Supprimer"
        HBox header = new HBox(titleLabel, deleteButton);
        header.setSpacing(10);
        header.setAlignment(Pos.CENTER);
        HBox.setHgrow(deleteButton, Priority.ALWAYS);
        header.setPadding(new Insets(20, 0, 0, 0)); // Ajout d'un peu d'espace en haut

        VBox content = new VBox(10, header, tableContainer);
        content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("content");

        content.getStyleClass().add("plants-background");


        mainPane.setCenter(content);
    }


    /**
     * Charge les plantes stockées dans des fichiers JSON du dossier 'Plants'.
     *
     * @return une liste observable contenant les objets Plant chargés depuis les fichiers JSON.
     */
    private ObservableList<Plant> loadPlants() {
        ObservableList<Plant> plants = FXCollections.observableArrayList();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            Path plantsDirectory = Paths.get("src/main/resources/Plants");
            // liste pour stocker les fichiers de plantes.
            List<File> plantFiles;
            try (Stream<Path> pathStream = Files.walk(plantsDirectory)) {
                // Récupère la liste des fichiers de plantes dans le répertoire 'Plants'.
                plantFiles = pathStream
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
            }
            // Parcourt chaque fichier de la liste des fichiers de plantes
            for (File plantFile : plantFiles) {
                // Lit l'objet Plant à partir du fichier JSON et l'ajoute à la liste observable plants
                Plant plant = objectMapper.readValue(plantFile, Plant.class);
                plants.add(plant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Retourne la liste observable des objets Plant chargés.
        return plants;
    }

    /** Méthode appelée lors du double-clic sur une plante de la liste */
    private void showPlantDetails(BorderPane mainPane, Plant plant) {
        // Afficher les détails de la plante
        // créer une nouvelle classe pour gérer l'affichage des détails de la plante.
        mainPane.setCenter(new PlantPage(plant));
    }

    /**
     * Supprime une plante spécifiée de la liste des plantes et du stockage (fichier JSON).
     * Affiche une boîte de dialogue de confirmation avant de procéder à la suppression.
     *
     * @param plant       La plante à supprimer.
     * @param plantsTable La table des plantes à mettre à jour après la suppression.
     */
    private void deletePlant(Plant plant, TableView<Plant> plantsTable) {
        // Afficher un pop-up de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer la plante");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous supprimer la plante " + plant.getSurnom() + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer la plante de la liste et du stockage
            plantsTable.getItems().remove(plant);

            // Supprimer la plante du stockage (fichier JSON)
            try {
                Path plantFile = Paths.get("src/main/resources/Plants/Plant" + plant.getId() + ".json");
                Files.deleteIfExists(plantFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
