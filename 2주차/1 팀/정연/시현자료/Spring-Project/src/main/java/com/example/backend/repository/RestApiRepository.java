package com.example.backend.repository;

import com.example.backend.entity.RestApi;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RestApiRepository extends JpaRepository<RestApi, String> {
    List<RestApi> findAll();
    boolean existsByEmail(String email);
    RestApi findByEmail(String email);
}
