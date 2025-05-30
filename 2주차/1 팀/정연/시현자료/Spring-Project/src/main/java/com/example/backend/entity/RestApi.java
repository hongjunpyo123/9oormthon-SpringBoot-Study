package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="회원")
public class RestApi {
    @Id
    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String phone;

    public RestApi(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public void patch(RestApi restApi) {
        if (restApi.password != null) {
            this.password = restApi.password;
        }
        if (restApi.name != null) {
            this.name = restApi.name;
        }
        if (restApi.phone != null) {
            this.phone = restApi.phone;
        }
    }
}
