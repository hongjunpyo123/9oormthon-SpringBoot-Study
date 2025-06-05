package com.example.iWishTheyWereAllDead.repository;

import com.example.iWishTheyWereAllDead.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
    List<WeatherEntity> findByCity(String city);
}
