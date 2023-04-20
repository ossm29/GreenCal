package com.example.greencal;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WeatherBox extends HBox {

    public WeatherBox() {
        String apiKey = "8eb0217ff93546d59eb223642232004";
        String city = "Gif-sur-Yvette";
        WeatherData weatherData = getTemperatureFromApi(city, apiKey);

        ImageView weatherIcon = new ImageView(new Image(weatherData.iconUrl, 50, 50, true, true));
        Label temperatureLabel = new Label(String.format(" %s : %.2f °C", city, weatherData.temperature));
        temperatureLabel.getStyleClass().add("temperature-label");

        HBox weatherBox = new HBox();
        weatherBox.setSpacing(10);
        weatherBox.setAlignment(Pos.CENTER);

        weatherBox.getChildren().addAll(weatherIcon, temperatureLabel);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH);
        String dateString = currentDate.format(formatter);

        Label dateLabel = new Label(dateString);
        dateLabel.getStyleClass().add("date-label");

        String season = getSeason(currentDate);
        Label seasonLabel = new Label(season);
        seasonLabel.getStyleClass().add("season-label");

        this.setSpacing(15);
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("top-bar");

        this.getChildren().addAll(dateLabel, weatherBox, seasonLabel);
    }


    public static WeatherData getTemperatureFromApi(String city, String apiKey) {
        try {
            String urlString = String.format("http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no", apiKey, city);
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();

            JSONObject jsonObject = new JSONObject(content.toString());
            JSONObject currentWeather = jsonObject.getJSONObject("current");
            double temp_c = currentWeather.getDouble("temp_c");
            String iconUrl = "http:" + currentWeather.getJSONObject("condition").getString("icon");
            return new WeatherData(temp_c, iconUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSeason(LocalDate date) {
        int month = date.getMonthValue();
        String season;

        if (month >= 3 && month <= 5) {
            season = "Printemps";
        } else if (month >= 6 && month <= 8) {
            season = "Été";
        } else if (month >= 9 && month <= 11) {
            season = "Automne";
        } else {
            season = "Hiver";
        }

        return season;
    }
    // Méthodes getTemperatureFromApi, getSeason, etc.
}
