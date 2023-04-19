package com.example.greencal;

import java.time.LocalDate;

/** Classe représentant une mesure de taille de plante
 * Une plante a pour attributs une liste d'éléments de cette classe
 * */
public class PlantSizeMeasurement {
    private double sizeInCm;
    private LocalDate date;

    public PlantSizeMeasurement(double sizeInCm, LocalDate date) {
        this.sizeInCm = sizeInCm;
        this.date = date;
    }

    // Getter et setter pour sizeInCm et date

    public double getSizeInCm() {
        return sizeInCm;
    }

    public void setSizeInCm(double sizeInCm) {
        this.sizeInCm = sizeInCm;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
