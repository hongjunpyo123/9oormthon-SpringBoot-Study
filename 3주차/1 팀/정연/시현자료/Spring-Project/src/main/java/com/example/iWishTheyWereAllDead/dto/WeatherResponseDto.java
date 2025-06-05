package com.example.iWishTheyWereAllDead.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherResponseDto {

    private String city;
    private Long longitude;
    private Long latitude;

    public WeatherResponseDto() {}

    public WeatherResponseDto(String city, Long longitude, Long latitude) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}

