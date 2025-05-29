package com.example.backend.entity;

import com.example.backend.repository.RestApiRepository;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birth;


    public void patch(RestApi restApi) {
        if (restApi.name != null) {
            this.name = restApi.name;
        }
        if (restApi.birth != null) {
            this.birth = restApi.birth;
        }
    }
}
