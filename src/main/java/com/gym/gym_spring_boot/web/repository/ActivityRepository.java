package com.gym.gym_spring_boot.web.repository;

import com.gym.gym_spring_boot.web.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
