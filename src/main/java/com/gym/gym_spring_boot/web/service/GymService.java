package com.gym.gym_spring_boot.web.service;

import com.gym.gym_spring_boot.web.dto.GymDto;
import com.gym.gym_spring_boot.web.models.Gym;

import java.util.List;

public interface GymService {
    List<GymDto> findAllGyms();
    Gym saveGym(GymDto gymDto);
    GymDto findGymById(Long gymId);
    void updateGym(GymDto club);
    void delete(Long gymId);
    List<GymDto> searchGyms(String query);
}
