package com.example.greencal;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;


/** Contrôleur associé au bouton myPlants permettant de consulter la liste des plantes */
public class ControlMyPlants implements EventHandler<ActionEvent> {

    private BorderPane mainPane;

    public ControlMyPlants(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public void showMyPlants(BorderPane mainPane) {
        // Titre "Mes Plantes"
        Label titleLabel = new Label("Mes Plantes");
        titleLabel.getStyleClass().add("title-label");

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

        plantsTable.getColumns().addAll(idColumn, imageColumn, surnomColumn, plantingDateColumn);
        plantsTable.setItems(loadPlants());

        // StackPane pour centrer horizontalement le tableau
        StackPane tableContainer = new StackPane(plantsTable);
        tableContainer.setAlignment(Pos.CENTER);

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

        VBox content = new VBox(10, titleLabel, tableContainer);
        content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("content");

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


    private void showPlantDetails(BorderPane mainPane, Plant plant) {
        // Afficher les détails de la plante et permettre à l'utilisateur de planifier des événements/tâches spécifiques
        // On peut créer une nouvelle méthode ou classe pour gérer l'affichage des détails de la plante et la planification des événements/tâches.
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        showMyPlants(mainPane);
    }
}
