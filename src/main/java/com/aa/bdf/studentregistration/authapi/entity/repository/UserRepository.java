package com.aa.bdf.studentregistration.authapi.entity.repository;

import com.aa.bdf.studentregistration.authapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserName(String userName);
}
