package com.example.backend.api;

import com.example.backend.dto.RestApiForm;
import com.example.backend.entity.RestApi;
import com.example.backend.service.RestApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
class RestApiController {
    @Autowired
    private RestApiService restApiService;
    //GET
    @GetMapping("/user")
    public List<RestApi> readAll() {
        return restApiService.readAll();
    }
    @GetMapping("/user/{email}")
    public RestApi readOne(@PathVariable String email) {
        return restApiService.readOne(email);
    }

    //POST
    @PostMapping("/user")
    public ResponseEntity<RestApi> create(@RequestBody RestApiForm dto) {
        RestApi created = restApiService.create(dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //UPDATE
    @PatchMapping("/user/{email}")
    public ResponseEntity<RestApi> update(@PathVariable String email, @RequestBody RestApiForm dto) {
        RestApi updated = restApiService.update(email, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/user/{email}")
    public ResponseEntity<RestApi> delete(@PathVariable String email) {
        RestApi deleted = restApiService.delete(email);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
