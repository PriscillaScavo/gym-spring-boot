package com.gym.gym_spring_boot.web.mapper;

import com.gym.gym_spring_boot.web.dto.ActivityDto;
import com.gym.gym_spring_boot.web.models.Activity;

public class ActivityMapper {
    public static Activity mapToActivity(ActivityDto activityDto) {
        return Activity.builder()
                .id(activityDto.getId())
                .name(activityDto.getName())
                .startTime(activityDto.getStartTime())
                .endTime(activityDto.getEndTime())
                .type(activityDto.getType())
                .createdOn(activityDto.getCreatedOn())
                .updatedOn(activityDto.getUpdatedOn())
                .gym(activityDto.getGym())
                .build();
    }

    public static ActivityDto mapToActivityDto(Activity activity) {
        return ActivityDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .startTime(activity.getStartTime())
                .endTime(activity.getEndTime())
                .type(activity.getType())
                .createdOn(activity.getCreatedOn())
                .updatedOn(activity.getUpdatedOn())
                .gym(activity.getGym())
                .build();
    }
}