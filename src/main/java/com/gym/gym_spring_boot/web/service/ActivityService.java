package com.gym.gym_spring_boot.web.service;

import com.gym.gym_spring_boot.web.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    void createActivity(Long clubId, ActivityDto activityDto);
    List<ActivityDto> findAllActivities();
    ActivityDto findByActivityId(Long activityId);
    void updateActivity(ActivityDto activityDto);
    void deleteActivity(long activityId);
}