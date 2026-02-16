package com.bm.WeatherApp.CONTROLLER;

import com.bm.WeatherApp.CLIENT.WeatherClient;
import com.bm.WeatherApp.DTOS.WeatherResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController{

    private final WeatherClient weatherClient;
    private final RedisTemplate<String,Object> redisTemplate;

    public WeatherController(WeatherClient weatherClient, RedisTemplate<String,Object> redisTemplate){
        this.weatherClient = weatherClient;
        this.redisTemplate=redisTemplate;
    }



    @PostMapping("/get/{city}")
    public WeatherResponse getWeather(@PathVariable String city) {
        try {

            WeatherResponse response = (WeatherResponse) redisTemplate.opsForValue().get(city);
            if (response == null) {
                response = weatherClient.getWeather(city);
                redisTemplate.opsForValue().set(city, response);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather data for city: " + city, e);
        }

    }

}
