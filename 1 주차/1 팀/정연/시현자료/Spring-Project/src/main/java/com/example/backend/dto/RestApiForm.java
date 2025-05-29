package com.example.backend.dto;


import com.example.backend.entity.RestApi;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class RestApiForm {
    private String name;
    private String birth;

    public RestApi toEntity(){
        return new RestApi(null, name, birth);
    }

}
