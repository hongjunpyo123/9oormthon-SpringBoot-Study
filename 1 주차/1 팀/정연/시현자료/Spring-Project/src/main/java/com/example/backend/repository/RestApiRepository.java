package com.example.backend.repository;

import com.example.backend.entity.RestApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface RestApiRepository extends CrudRepository<RestApi, Long> {
    @Override
    ArrayList<RestApi> findAll();
}
