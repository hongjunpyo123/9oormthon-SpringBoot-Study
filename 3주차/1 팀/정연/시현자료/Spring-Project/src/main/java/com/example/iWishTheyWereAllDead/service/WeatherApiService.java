package com.example.iWishTheyWereAllDead.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class WeatherApiService {

    @Value("${WEATHER_API_SERVICE_KEY}")
    private String serviceKey;

    private final RestTemplate restTemplate;

    public WeatherApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 초단기 실황 조회
    public String getUltraSrtNcst(String baseDate, String baseTime, String nx, String ny) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    // 초단기예보
    public String getUltraSrtFcst(String baseDate, String baseTime, String nx, String ny) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    // 단기예보
    public String getVilageFcst(String baseDate, String baseTime, String nx, String ny) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }
}
