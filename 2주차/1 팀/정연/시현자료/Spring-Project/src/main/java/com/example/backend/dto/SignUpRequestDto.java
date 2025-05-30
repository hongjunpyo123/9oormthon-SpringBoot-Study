package com.example.backend.dto;


import com.example.backend.entity.RestApi;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;

    public RestApi toEntity(){
        return new RestApi(email, password, name, phone);
    }

}
