package com.bm.WeatherApp;

public class WeatherApiException extends RuntimeException {
    public WeatherApiException(String message) {
        super(message);
    }
}
