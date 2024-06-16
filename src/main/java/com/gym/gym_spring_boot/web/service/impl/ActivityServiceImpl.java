package com.gym.gym_spring_boot.web.service.impl;

import com.gym.gym_spring_boot.web.dto.ActivityDto;
import com.gym.gym_spring_boot.web.mapper.ActivityMapper;
import com.gym.gym_spring_boot.web.models.Activity;
import com.gym.gym_spring_boot.web.models.Gym;
import com.gym.gym_spring_boot.web.repository.ActivityRepository;
import com.gym.gym_spring_boot.web.repository.GymRepository;
import com.gym.gym_spring_boot.web.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gym.gym_spring_boot.web.mapper.ActivityMapper.mapToActivity;
import static com.gym.gym_spring_boot.web.mapper.ActivityMapper.mapToActivityDto;

@Service
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;
    private GymRepository gymRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, GymRepository gymRepository) {
        this.activityRepository = activityRepository;
        this.gymRepository = gymRepository;
    }

    @Override
    public void createActivity(Long gymId, ActivityDto activityDto) {
        Gym gym = gymRepository.findById(gymId).get();
        Activity activity = mapToActivity(activityDto);
        activity.setGym(gym);
        activityRepository.save(activity);
    }

    @Override
    public List<ActivityDto> findAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(ActivityMapper::mapToActivityDto).collect(Collectors.toList());
    }

    @Override
    public ActivityDto findByActivityId(Long activityId) {
        Activity activity = activityRepository.findById(activityId).get();
        return mapToActivityDto(activity);
    }

    @Override
    public void updateActivity(ActivityDto activityDto) {
        Activity activity = mapToActivity(activityDto);
        activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(long activityId) {
        activityRepository.deleteById(activityId);
    }
}
