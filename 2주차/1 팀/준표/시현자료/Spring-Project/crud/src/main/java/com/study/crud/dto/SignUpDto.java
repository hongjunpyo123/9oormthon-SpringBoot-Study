package com.study.crud.dto;

import com.study.crud.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String id;
    private String name;
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
