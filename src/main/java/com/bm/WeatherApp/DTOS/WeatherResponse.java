package com.bm.WeatherApp.DTOS;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherResponse {
    public String city;
    public double temperature;
}
