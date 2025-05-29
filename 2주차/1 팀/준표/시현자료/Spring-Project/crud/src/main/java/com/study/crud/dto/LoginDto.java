package com.study.crud.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LoginDto {
    private String id;
    private String password;
}
