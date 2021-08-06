package com.aa.bdf.studentregistration.authapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOGIN_USER")
public class UserEntity {

    @Id
    private Long id;
    private String userName;
    private String password;
    private String email;
}
