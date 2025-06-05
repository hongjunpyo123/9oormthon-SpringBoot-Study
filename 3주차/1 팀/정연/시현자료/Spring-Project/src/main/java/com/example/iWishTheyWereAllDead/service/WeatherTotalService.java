package com.example.iWishTheyWereAllDead.service;

import com.example.iWishTheyWereAllDead.entity.WeatherEntity;
import com.example.iWishTheyWereAllDead.repository.WeatherRepository;
import com.example.iWishTheyWereAllDead.util.WeatherTimeUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WeatherTotalService {

    private final WeatherRepository weatherRepository;
    private final WeatherApiService weatherApiService;

    public WeatherTotalService(WeatherRepository weatherRepository, WeatherApiService weatherApiService) {
        this.weatherRepository = weatherRepository;
        this.weatherApiService = weatherApiService;
    }

    private WeatherEntity getFirstEntity(String city) {
        List<WeatherEntity> entityList = weatherRepository.findByCity(city);
        if (entityList.isEmpty()) {
            throw new IllegalArgumentException("해당 도시가 DB에 없습니다.");
        }
        return entityList.get(0);  // 가장 첫 번째 값 사용
    }

    public String getUltraSrtNcstByCity(String city) throws IOException {
        WeatherEntity entity = getFirstEntity(city);

        String nx = entity.getLongitude().toString();
        String ny = entity.getLatitude().toString();

        String baseDate = WeatherTimeUtil.getBaseDate();
        String baseTime = WeatherTimeUtil.getBaseTime();

        return weatherApiService.getUltraSrtNcst(baseDate, baseTime, nx, ny);
    }

    public String getUltraSrtFcstByCity(String city) throws IOException {
        WeatherEntity entity = getFirstEntity(city);

        String nx = entity.getLongitude().toString();
        String ny = entity.getLatitude().toString();

        String baseDate = WeatherTimeUtil.getBaseDate();
        String baseTime = WeatherTimeUtil.getBaseTime();

        return weatherApiService.getUltraSrtFcst(baseDate, baseTime, nx, ny);
    }

    public String getVilageFcstByCity(String city) throws IOException {
        WeatherEntity entity = getFirstEntity(city);

        String nx = entity.getLongitude().toString();
        String ny = entity.getLatitude().toString();

        String baseDate = WeatherTimeUtil.getBaseDate();
        String baseTime = WeatherTimeUtil.getBaseTime();

        return weatherApiService.getVilageFcst(baseDate, baseTime, nx, ny);
    }
}
