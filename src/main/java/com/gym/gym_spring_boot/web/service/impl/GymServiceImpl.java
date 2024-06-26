package com.gym.gym_spring_boot.web.service.impl;

import com.gym.gym_spring_boot.web.dto.GymDto;
import com.gym.gym_spring_boot.web.mapper.GymMapper;
import com.gym.gym_spring_boot.web.models.Gym;
import com.gym.gym_spring_boot.web.models.UserEntity;
import com.gym.gym_spring_boot.web.repository.GymRepository;
import com.gym.gym_spring_boot.web.repository.UserRepository;
import com.gym.gym_spring_boot.web.security.SecurityUtil;
import com.gym.gym_spring_boot.web.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gym.gym_spring_boot.web.mapper.GymMapper.mapToGym;
import static com.gym.gym_spring_boot.web.mapper.GymMapper.mapToGymDto;



@Service
public class GymServiceImpl implements GymService {
    private GymRepository gymRepository;
    private UserRepository userRepository;
    @Autowired
    public GymServiceImpl(GymRepository gymRepository, UserRepository userRepository) {
        this.gymRepository = gymRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<GymDto> findAllGyms() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms.stream().map(GymMapper::mapToGymDto).collect(Collectors.toList());
    }
    @Override
    public Gym saveGym(GymDto gymDto)  {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        System.out.println("USER => " + username);
        Gym gym = mapToGym(gymDto);
        gym.setCreatedBy(user);
        return gymRepository.save(gym);
    }
    @Override
    public GymDto findGymById(Long gymId) {
        Gym gym = gymRepository.findById(gymId).get();
        return mapToGymDto(gym);
    }
    @Override
    public void updateGym(GymDto gymDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Gym gym = mapToGym(gymDto);
        gym.setCreatedBy(user);
        gymRepository.save(gym);
    }
    @Override
    public void delete(Long gymId) {
        gymRepository.deleteById(gymId);
    }
    @Override
    public List<GymDto> searchGyms(String query) {
        List<Gym> gyms = gymRepository.searchGyms(query);
        return gyms.stream().map(gym -> mapToGymDto(gym)).collect(Collectors.toList());
    }
}
