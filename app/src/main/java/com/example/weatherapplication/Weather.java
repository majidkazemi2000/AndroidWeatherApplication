package com.example.weatherapplication;

public class Weather {
    private int weather_id;
    private int temp;
    private int max_temp;
    private int min_temp;
    private int humidity;
    private int speed_wind;
    private String weather_text;
    private int weather_icon;

    public int getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(int weather_id) {
        this.weather_id = weather_id;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(int max_temp) {
        this.max_temp = max_temp;
    }

    public int getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(int min_temp) {
        this.min_temp = min_temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getSpeed_wind() {
        return speed_wind;
    }

    public void setSpeed_wind(int speed_wind) {
        this.speed_wind = speed_wind;
    }

    public String getWeather_text() {
        return weather_text;
    }

    public void setWeather_text(String weather_text) {
        this.weather_text = weather_text;
    }

    public int getWeather_icon() {
        return weather_icon;
    }

    public void setWeather_icon(int weather_icon) {
        this.weather_icon = weather_icon;
    }
}
