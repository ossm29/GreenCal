package com.example.greencal;

public class WeatherData {
    public final double temperature;
    public final String iconUrl;

    public double getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public WeatherData(double temperature, String iconUrl) {
        this.temperature = temperature;
        this.iconUrl = iconUrl;
    }
}
