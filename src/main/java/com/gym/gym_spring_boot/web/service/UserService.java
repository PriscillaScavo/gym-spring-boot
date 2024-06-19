package com.gym.gym_spring_boot.web.service;


import com.gym.gym_spring_boot.web.dto.RegistrationDto;
import com.gym.gym_spring_boot.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
