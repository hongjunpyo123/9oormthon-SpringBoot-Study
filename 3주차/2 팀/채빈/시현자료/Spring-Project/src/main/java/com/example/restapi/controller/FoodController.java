package com.example.restapi.controller;

import com.example.restapi.dto.FoodInfoDto;
import com.example.restapi.dto.FoodResponse;
import com.example.restapi.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public FoodResponse getFoods(
            @RequestParam(required = false) String sigun,
            @RequestParam(defaultValue = "1") int page) {

        List<FoodInfoDto> result = foodService.getFoodList(sigun);
        String title = generateTitle(sigun);

        return new FoodResponse(title, result);
    }

    private String generateTitle(String sigun) {
        return sigun == null ?
                "경기도 전체 맛집 목록" :
                String.format("경기도 %s 맛집 목록", sigun);
    }
}