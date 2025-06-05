package com.example.iWishTheyWereAllDead.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherTimeUtil {

    public static String getBaseDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String getBaseTime() {
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();
        int minute = now.getMinute();

        // 30분 단위 내림
        if (minute < 30) {
            minute = 0;
        } else {
            minute = 30;
        }

        return String.format("%02d%02d", hour, minute);
    }
}
