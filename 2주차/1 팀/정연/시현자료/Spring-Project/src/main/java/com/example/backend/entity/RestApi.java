package com.example.backend.entity;

import com.example.backend.repository.RestApiRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestApi {
    @Id
    @Column
    private String email;

    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String phone;



    public void patch(RestApi restApi) {
        if (restApi.id != null) {
            this.id = restApi.id;
        }
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
