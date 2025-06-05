package com.example.iWishTheyWereAllDead.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherRequestDto {

    private String city;

    public WeatherRequestDto() {}

    public WeatherRequestDto(String city) {
        this.city = city;
    }

}
