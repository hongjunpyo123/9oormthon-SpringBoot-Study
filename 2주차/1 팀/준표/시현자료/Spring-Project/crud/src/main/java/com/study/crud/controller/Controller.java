package com.study.crud.controller;

import com.study.crud.dto.FindDto;
import com.study.crud.dto.LoginDto;
import com.study.crud.dto.SignUpDto;
import com.study.crud.dto.UpdateDto;
import com.study.crud.entity.UserEntity;
import com.study.crud.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            String LoginResponse = service.login(loginDto);
            return ResponseEntity.ok().body(LoginResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        try {
            String signUpResponse = service.signUp(signUpDto);
            return ResponseEntity.ok().body(signUpResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        System.out.println(id);
        try {
            FindDto user = service.findById(id);
            return ResponseEntity.ok().body(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<FindDto> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateDto updateDto) {
        try {
            String updateResponse = service.update(id, updateDto);
            return ResponseEntity.ok().body(updateResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            String deleteResponse = service.delete(id);
            return ResponseEntity.ok().body(deleteResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
