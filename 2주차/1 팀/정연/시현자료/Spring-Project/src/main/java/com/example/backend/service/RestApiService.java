package com.example.backend.service;
import com.example.backend.dto.RestApiForm;
import com.example.backend.entity.RestApi;
import com.example.backend.repository.RestApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestApiService {
    @Autowired
    private RestApiRepository restApiRepository;

    public List<RestApi> readAll() {
        return restApiRepository.findAll();
    }

    public RestApi readOne(String email) {
        return restApiRepository.findById(email).orElse(null);
    }

    public RestApi create(RestApiForm dto) {
        RestApi restApi = dto.toEntity();
        if (restApiRepository.existsById(dto.getEmail())) {
            return null;
        }
        return restApiRepository.save(restApi);
    }

    public RestApi update(String email, RestApiForm dto) {
        RestApi restApi = dto.toEntity();
        RestApi target = restApiRepository.findById(email).orElse(null);
        if (target == null) {
            return null;
        }
        target.patch(restApi);
        RestApi updated = restApiRepository.save(target);
        return updated;
    }

    public RestApi delete(String email) {
        RestApi target = restApiRepository.findById(email).orElse(null);
        if(target == null) {
            return null;
        }

        restApiRepository.delete(target);
        return target;
    }
}
