package com.example.backend.service;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.LoginResponseDto;
import com.example.backend.dto.SignUpRequestDto;
import com.example.backend.entity.RestApi;
import com.example.backend.repository.RestApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    public RestApi create(SignUpRequestDto dto) {
        RestApi restApi = dto.toEntity();
        if (restApiRepository.existsById(dto.getEmail())) {
            return null;
        }
        return restApiRepository.save(restApi);
    }

    public RestApi update(String email, LoginRequestDto dto) {
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

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        if(!restApiRepository.existsByEmail(loginRequestDto.getEmail())) {
            return null;
        } else {
            RestApi restApi = restApiRepository.findByEmail(loginRequestDto.getEmail());
            if(loginRequestDto.getPassword().equals(restApi.getPassword())){
                return new LoginResponseDto(loginRequestDto.getEmail());
            } else {
                return null;
            }
        }
    }
}
