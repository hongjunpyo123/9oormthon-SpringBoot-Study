package com.example.backend.dto;


import com.example.backend.entity.RestApi;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;

    public RestApi toEntity(){
        return new RestApi(email, password);
    }
}