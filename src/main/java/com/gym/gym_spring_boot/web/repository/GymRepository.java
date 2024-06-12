package com.gym.gym_spring_boot.web.repository;

import com.gym.gym_spring_boot.web.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findByName(String url);
    @Query("SELECT c from Gym c WHERE c.name LIKE CONCAT('%', :query, '%')")
    List<Gym> searchGyms(String query);
}
