package com.example.greencal;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** classe qui créé l'id des plantes afin de gérer les nouvelles plantes et nommer les fichiers */
public class PlantID {
    public static int id;

    /* Initialise l'ID avec la valeur maximale des ID des plantes dans le dossier Plants, puis ajoute 1 */
    static {
        File plantsDirectory = new File("src/main/resources/Plants");
        if (plantsDirectory.exists() && plantsDirectory.isDirectory()) {
            FilenameFilter jsonFilter = (dir, name) -> name.toLowerCase().endsWith(".json");
            File[] jsonFiles = plantsDirectory.listFiles(jsonFilter);
            if (jsonFiles != null) {
                int maxId = Arrays.stream(jsonFiles)
                        .map(file -> {
                            Pattern pattern = Pattern.compile("Plant(\\d+)\\.json");
                            Matcher matcher = pattern.matcher(file.getName());
                            return matcher.matches() ? Integer.parseInt(matcher.group(1)) : 0;
                        })
                        .max(Integer::compare)
                        .orElse(0);
                id = maxId + 1;
            } else {
                id = 1;
            }
        } else {
            id = 1;
        }
    }
}
