package com.gym.gym_spring_boot.web.dto;

import com.gym.gym_spring_boot.web.models.Gym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private String type;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Gym gym;
}