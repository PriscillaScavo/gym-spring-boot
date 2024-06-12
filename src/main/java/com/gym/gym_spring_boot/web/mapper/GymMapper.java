package com.gym.gym_spring_boot.web.mapper;

import com.gym.gym_spring_boot.web.models.Gym;
import com.gym.gym_spring_boot.web.dto.GymDto;

public class GymMapper {
    public static Gym mapToGym(GymDto gymDto){
        Gym gym = Gym.builder()
                .id(gymDto.getId())
                .name(gymDto.getName())
                .address(gymDto.getAddress())
                .photoUrl(gymDto.getPhotoUrl())
                .createdOn(gymDto.getCreatedOn())
                .updatedOn(gymDto.getUpdatedOn())
                .build();
        return gym;
    }

    public static GymDto mapToGymDto(Gym gym){
        GymDto gymDto = GymDto.builder()
                .id(gym.getId())
                .name(gym.getName())
                .address(gym.getAddress())
                .photoUrl(gym.getPhotoUrl())
                .createdOn(gym.getCreatedOn())
                .updatedOn(gym.getUpdatedOn())
                .build();
        return gymDto;
    }
}
