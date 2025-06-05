package com.example.iWishTheyWereAllDead.api;

import com.example.iWishTheyWereAllDead.service.WeatherTotalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherTotalController {

    private final WeatherTotalService weatherTotalService;

    public WeatherTotalController(WeatherTotalService weatherTotalService) {
        this.weatherTotalService = weatherTotalService;
    }

    @GetMapping("/weather/by-city/ncst")
    public String getUltraSrtNcstByCity(@RequestParam String city) {
        try {
            return weatherTotalService.getUltraSrtNcstByCity(city);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/weather/by-city/ultra-forecast")
    public String getUltraSrtFcstByCity(@RequestParam String city) {
        try {
            return weatherTotalService.getUltraSrtFcstByCity(city);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/weather/by-city/forecast")
    public String getVilageFcstByCity(@RequestParam String city) {
        try {
            return weatherTotalService.getVilageFcstByCity(city);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
