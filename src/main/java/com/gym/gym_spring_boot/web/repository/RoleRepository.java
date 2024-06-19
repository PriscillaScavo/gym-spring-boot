package com.gym.gym_spring_boot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gym.gym_spring_boot.web.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
