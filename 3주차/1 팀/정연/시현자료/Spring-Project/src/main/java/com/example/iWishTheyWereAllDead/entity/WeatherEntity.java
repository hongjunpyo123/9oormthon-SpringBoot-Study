package com.example.iWishTheyWereAllDead.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "격자_위경도")
public class WeatherEntity {

    @Id
    @Column(name = "행정구역코드")
    private String adminCode;

    @Column(name = "1단계")
    private String city;

    @Column(name = "격자 X")
    private Long longitude;

    @Column(name = "격자 Y")
    private Long latitude;



    public WeatherEntity() {}

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
}
