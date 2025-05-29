package com.study.crud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindDto {
    private String id;
    private String name;
}
