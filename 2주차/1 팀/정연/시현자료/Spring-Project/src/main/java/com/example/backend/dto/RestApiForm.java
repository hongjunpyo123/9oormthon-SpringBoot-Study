package com.example.backend.dto;


import com.example.backend.entity.RestApi;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RestApiForm {
    private String email;
    private String id;
    private String password;
    private String name;
    private String phone;

    public RestApi toEntity(){
        return new RestApi(email, id, password, name, phone);
    }

}
