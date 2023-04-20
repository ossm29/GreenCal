package com.example.greencal;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Classe qui représente une plante*/
public class Plant {
    private int id;
    private String surnom;
    private String nomScientifique;
    private String imagePath;

    private ArrayList<String> notes = new ArrayList<>();

    private ArrayList<PlantSizeMeasurement> sizeMeasurements = new ArrayList<>();

    private LocalDate plantationDate;
    private LocalDate rempotageDate;

    private ArrayList<LocalDate> rempotageDates = new ArrayList<>();

    private LocalDate arrosageDate;

    private ArrayList<LocalDate> arrosageDates = new ArrayList<>();

    private LocalDate entretienDate;

    private ArrayList<LocalDate> entretienDates = new ArrayList<>();

    private LocalDate recolteDate;

    private ArrayList<LocalDate> recolteDates = new ArrayList<>();


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
        this.recolteDates.add(rempotageDate);

        this.arrosageDate = arrosageDate;
        this.arrosageDates.add(arrosageDate);

        this.entretienDate = entretienDate;
        this.entretienDates.add(entretienDate);

        this.recolteDate = recolteDate;
        this.recolteDates.add(recolteDate);
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

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public void addNote(String note) {
        this.notes.add(note);
        PlantDataHandler.savePlantToFile(this, "src/main/resources/Plants/Plant" + this.id + ".json");
    }

    public ArrayList<PlantSizeMeasurement> getSizeMeasurements() {
        return sizeMeasurements;
    }

    public void setSizeMeasurements(ArrayList<PlantSizeMeasurement> sizeMeasurements) {
        this.sizeMeasurements = sizeMeasurements;
    }

    public void addSizeMeasurement(PlantSizeMeasurement sizeMeasurements) {
        this.sizeMeasurements.add(sizeMeasurements);
        PlantDataHandler.savePlantToFile(this, "src/main/resources/Plants/Plant" + this.id + ".json");

    }

    public ArrayList<LocalDate> getArrosageDates() {
        return arrosageDates;
    }

    public void setArrosageDates(ArrayList<LocalDate> arrosageDates) {
        this.arrosageDates = arrosageDates;
    }

    public ArrayList<LocalDate> getEntretienDates() {
        return entretienDates;
    }

    public void setEntretienDates(ArrayList<LocalDate> entretienDates) {
        this.entretienDates = entretienDates;
    }

    public ArrayList<LocalDate> getRecolteDates() {
        return recolteDates;
    }

    public void setRecolteDates(ArrayList<LocalDate> recolteDates) {
        this.recolteDates = recolteDates;
    }

    public void addArrosageDate(LocalDate date) {
        arrosageDates.add(date);
        this.arrosageDate = date;
        PlantDataHandler.savePlantToFile(this, "src/main/resources/Plants/Plant" + this.id + ".json");

    }

    public void addEntretienDate(LocalDate date) {
        entretienDates.add(date);
        this.entretienDate = date;
        PlantDataHandler.savePlantToFile(this, "src/main/resources/Plants/Plant" + this.id + ".json");

    }

    public void addRecolteDate(LocalDate date) {
        recolteDates.add(date);
        this.recolteDate = date;
        PlantDataHandler.savePlantToFile(this, "src/main/resources/Plants/Plant" + this.id + ".json");
    }

    public ArrayList<LocalDate> getRempotageDates() {
        return rempotageDates;
    }

    public void setRempotageDates(ArrayList<LocalDate> rempotageDates) {
        this.rempotageDates = rempotageDates;
    }

    public void addRempotageDate(LocalDate date) {
        rempotageDates.add(date);
        this.rempotageDate = date;
        PlantDataHandler.savePlantToFile(this, "src/main/resources/Plants/Plant" + this.id + ".json");

    }

}

