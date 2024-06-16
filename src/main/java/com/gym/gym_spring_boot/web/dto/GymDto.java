package com.gym.gym_spring_boot.web.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GymDto {
    private Long id;
    @NotEmpty(message = "Gym name should not be empty")
    private String name;
    @NotEmpty(message = "Gym address should not be empty")
    private String address;
    private String photoUrl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<ActivityDto> activities;
}
