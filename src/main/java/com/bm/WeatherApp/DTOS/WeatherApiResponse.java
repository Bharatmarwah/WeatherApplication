package com.bm.WeatherApp.DTOS;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherApiResponse {
    private Current current;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Current {
        private double temp_c;
    }

}
