package com.example.backend.api;
import com.example.backend.dto.RestApiForm;
import com.example.backend.entity.RestApi;
import com.example.backend.repository.RestApiRepository;
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
    private RestApiRepository restApiRepository;

    //Create
    @PostMapping("/user")
    public RestApi create(@RequestBody RestApiForm form) {
        RestApi saved = form.toEntity();
        return restApiRepository.save(saved);
    }

    //Read
    @GetMapping("/user")
    public List<RestApi> readAll() {
        return restApiRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public RestApi readOne(@PathVariable Long id) {
        return restApiRepository.findById(id).orElse(null);
    }

    //Update
    @PatchMapping("/user/{id}")
    public ResponseEntity<RestApi> update(@PathVariable Long id, @RequestBody RestApiForm form) {
        RestApi restApi = form.toEntity();
        RestApi target = restApiRepository.findById(id).orElse(null);
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        target.patch(restApi);
        RestApi updated = restApiRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //Delete
    @DeleteMapping("/user/{id}")
    public ResponseEntity<RestApi> delete(@PathVariable Long id) {
        RestApi target = restApiRepository.findById(id).orElse(null);
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        restApiRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
