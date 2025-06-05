package com.example.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class FoodResponse {
    private String title;
    private List<FoodInfoDto> data;
}