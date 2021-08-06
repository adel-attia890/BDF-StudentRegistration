package com.aa.bdf.studentregistration.authapi.service;

import com.aa.bdf.studentregistration.authapi.entity.UserEntity;
import com.aa.bdf.studentregistration.authapi.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(userName);
        return new User(userEntity.getUserName(), userEntity.getPassword(), new ArrayList<>());
    }
}
