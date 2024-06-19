package com.gym.gym_spring_boot.web.mapper;

import com.gym.gym_spring_boot.web.models.Gym;
import com.gym.gym_spring_boot.web.dto.GymDto;

import java.util.stream.Collectors;

import static com.gym.gym_spring_boot.web.mapper.ActivityMapper.mapToActivityDto;

public class GymMapper {
    public static Gym mapToGym(GymDto gymDto){
        Gym gym = Gym.builder()
                .id(gymDto.getId())
                .name(gymDto.getName())
                .address(gymDto.getAddress())
                .photoUrl(gymDto.getPhotoUrl())
                .createdBy(gymDto.getCreatedBy())
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
                .createdBy(gym.getCreatedBy())
                .createdOn(gym.getCreatedOn())
                .updatedOn(gym.getUpdatedOn())
                .activities(gym.getActivities().stream().map(activity -> mapToActivityDto(activity)).collect(Collectors.toList()))
                .build();
        return gymDto;
    }
}
