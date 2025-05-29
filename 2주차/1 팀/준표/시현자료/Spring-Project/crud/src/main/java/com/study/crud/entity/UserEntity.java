package com.study.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name ="사용자")
public class UserEntity {
    @Id
    @Column(length = 255)
    private String id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String password;
}
