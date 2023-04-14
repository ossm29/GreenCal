package com.example.greencal;

import java.time.LocalDate;

/** Classe qui représente une plante*/
public class Plant {
    private int id;
    private String surnom;
    private String nomScientifique;
    private String imagePath;
    private LocalDate plantationDate;
    private LocalDate rempotageDate;
    private LocalDate arrosageDate;
    private LocalDate entretienDate;
    private LocalDate recolteDate;

    /** Constructeur sans argument */
    public Plant() {
    }

    public Plant(String surnom, String nomScientifique, String imagePath, LocalDate plantationDate, LocalDate rempotageDate, LocalDate arrosageDate, LocalDate entretienDate, LocalDate recolteDate) {
        this.id = PlantID.id;
        this.surnom = surnom;
        this.nomScientifique = nomScientifique;
        this.imagePath = imagePath;
        this.plantationDate = plantationDate;
        this.rempotageDate = rempotageDate;
        this.arrosageDate = arrosageDate;
        this.entretienDate = entretienDate;
        this.recolteDate = recolteDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getNomScientifique() {
        return nomScientifique;
    }

    public void setNomScientifique(String nomScientifique) {
        this.nomScientifique = nomScientifique;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDate getPlantationDate() {
        return plantationDate;
    }

    public void setPlantationDate(LocalDate plantationDate) {
        this.plantationDate = plantationDate;
    }

    public LocalDate getRempotageDate() {
        return rempotageDate;
    }

    public void setRempotageDate(LocalDate rempotageDate) {
        this.rempotageDate = rempotageDate;
    }

    public LocalDate getArrosageDate() {
        return arrosageDate;
    }

    public void setArrosageDate(LocalDate arrosageDate) {
        this.arrosageDate = arrosageDate;
    }

    public LocalDate getEntretienDate() {
        return entretienDate;
    }

    public void setEntretienDate(LocalDate entretienDate) {
        this.entretienDate = entretienDate;
    }

    public LocalDate getRecolteDate() {
        return recolteDate;
    }

    public void setRecolteDate(LocalDate recolteDate) {
        this.recolteDate = recolteDate;
    }
}

