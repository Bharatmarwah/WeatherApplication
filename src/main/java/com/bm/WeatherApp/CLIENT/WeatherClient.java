package com.bm.WeatherApp.CLIENT;

import com.bm.WeatherApp.CONFIGURATION.WebClientConfig;
import com.bm.WeatherApp.DTOS.WeatherApiResponse;
import com.bm.WeatherApp.DTOS.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherClient {

   private final WebClientConfig webClientConfig;

   @Value("${weather.api.key}")
   private String key;


   public WeatherClient(WebClientConfig webClientConfig) {
       this.webClientConfig = webClientConfig;
   }

   public WeatherResponse getWeather(String city){
       WeatherApiResponse response = webClientConfig
               .webClientBuilder()
               .build()
               .get()
               .uri(uriBuilder -> uriBuilder
                       .scheme("http")
                       .host("api.weatherapi.com")
                       .path("/v1/current.json")
                       .queryParam("key", key)
                       .queryParam("q", city)
                       .queryParam("aqi", "yes")
                       .build())
               .retrieve()
               .bodyToMono(WeatherApiResponse.class).block();

       if (response == null || response.getCurrent() == null) {
           throw new RuntimeException("Failed to fetch weather data for city: " + city);
       }

       return new WeatherResponse(city,response.getCurrent().getTemp_c());

   }
}

