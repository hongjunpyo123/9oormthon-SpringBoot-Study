package com.example.backend.api;

import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.LoginResponseDto;
import com.example.backend.dto.SignUpRequestDto;
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
    private final RestApiService restApiService;
    @Autowired
    public RestApiController(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    //GET
    @GetMapping("/user")
    public ResponseEntity<?> readAll() {
        List<RestApi> readAll = restApiService.readAll();
        return (readAll != null)?
                ResponseEntity.status(HttpStatus.OK).body(readAll):
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> readOne(@PathVariable String email) {
        RestApi readOne = restApiService.readOne(email);
        return (readOne != null)?
                ResponseEntity.status(HttpStatus.OK).body(readOne):
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //POST
    @PostMapping("/user")
    public ResponseEntity<RestApi> create(@RequestBody SignUpRequestDto signUpRequestDto) {
        RestApi created = restApiService.create(signUpRequestDto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //UPDATE
    @PatchMapping("/user/{email}")
    public ResponseEntity<RestApi> update(@PathVariable String email, @RequestBody LoginRequestDto dto) { //오류 직접 해결해 보십시오(내일까지)
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

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info(loginRequestDto.getEmail()); //postman으로 login 요청해보시고 객체에 매핑되는 필드값 출력 한번 해보세요
        log.info(loginRequestDto.getPassword());
        LoginResponseDto loginResponseDto = restApiService.login(loginRequestDto);
        if(loginResponseDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        }
    }

}
