package com.study.crud.service;
import com.study.crud.dto.FindDto;
import com.study.crud.dto.LoginDto;
import com.study.crud.dto.SignUpDto;
import com.study.crud.dto.UpdateDto;
import com.study.crud.entity.UserEntity;
import com.study.crud.repository.Repository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Slf4j
public class Service {
    private final BCryptPasswordEncoder hash;
    private final Repository repository;


    public Service(BCryptPasswordEncoder hash, Repository repository) {
        this.hash = hash;
        this.repository = repository;
    }

    public String signUp(SignUpDto signUpDto) {
        if(repository.existsById(signUpDto.getId())) {
            log.info("이미 가입된 유저입니다.");
            throw new RuntimeException("이미 가입된 유저입니다.");
        }
        signUpDto.setPassword(hash.encode(signUpDto.getPassword()));
        repository.save(signUpDto.toEntity());

        return "회원가입 성공";
    }

    public String login(LoginDto loginDto) {

        if(!repository.existsById(loginDto.getId())) {
            log.info("가입된 유저가 없습니다.");
            throw new RuntimeException("가입된 유저가 없습니다.");
        }

        UserEntity user = repository.findById(loginDto.getId()).orElse(null);
        if(hash.matches(loginDto.getPassword(), user.getPassword())) {
            log.info("로그인 성공");
            return "로그인 성공";
        } else {
            log.info("비밀번호가 일치하지 않습니다.");
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    public List<FindDto> findAll() {
        List<UserEntity> users =  repository.findAll();
        return users.stream()
                .map(user -> FindDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build())
                .toList();
    }

    public FindDto findById(String id) {
        UserEntity user = repository.findById(id).orElse(null);
        if(user == null) {
            log.info("해당 ID의 유저가 없습니다.");
            throw new RuntimeException("해당 ID의 유저가 없습니다.");
        } else {
            log.info("유저 정보 조회 성공");
            return FindDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .build();
        }
    }

    public String update(String id, UpdateDto updateDto) {
        if(!repository.existsById(id)) {
            log.info("해당 ID의 유저가 없습니다.");
            throw new RuntimeException("해당 ID의 유저가 없습니다.");
        } else {
            UserEntity user = repository.findById(id).orElse(null);
            user.setName(updateDto.getName());
            user.setPassword(hash.encode(updateDto.getPassword()));
            repository.save(user);
            return "유저 정보 업데이트 성공";
        }
    }

    @Transactional
    public String delete(String id) {
        if(!repository.existsById(id)) {
            log.info("해당 ID의 유저가 없습니다.");
            throw new RuntimeException("해당 ID의 유저가 없습니다.");
        } else {
            repository.deleteById(id);
            return "유저 삭제 성공";
        }
    }



}
