package com.study.crud.repository;

import com.study.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<UserEntity, String> {
    boolean existsById(String id);
}
