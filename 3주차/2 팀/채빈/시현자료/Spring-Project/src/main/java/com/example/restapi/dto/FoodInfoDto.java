package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodInfoDto {

    @JsonProperty("식당이름")
    private String bizplcNm;

    @JsonProperty("도로명주소")
    private String refineRoadnmAddr;  // 필드명 영문 유지

    @JsonProperty("지번주소")
    private String refineLotnoAddr;

    @JsonProperty("위도")
    private String refineWgs84Lat;

    @JsonProperty("경도")
    private String refineWgs84Logt;
}