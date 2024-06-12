package com.gym.gym_spring_boot.web.controller;

import com.gym.gym_spring_boot.web.dto.GymDto;
import com.gym.gym_spring_boot.web.models.Gym;
import com.gym.gym_spring_boot.web.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GymController {
    private GymService gymService;

    @Autowired
    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("/gyms")
    public String listGym(Model model) {
        List<GymDto> gyms = gymService.findAllGyms();
        model.addAttribute("gyms", gyms );
        return "gyms-list";
    }
    @GetMapping("/gyms/new")
    public String createGymForm(Model model) {
        Gym gym = new Gym();
        model.addAttribute("gym", gym);
        return "gyms-create";
    }
    @PostMapping("/gyms/new")
    public String saveGym(@Valid @ModelAttribute("gym") GymDto gymDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("gym", gymDto);
            return "gyms-create";
        }
        gymService.saveGym(gymDto);
        return "redirect:/gyms";
    }

    @GetMapping("/gyms/{gymId}/edit")
    public String editGymForm(@PathVariable("gymId") Long gymId, Model model) {
        GymDto gym = gymService.findGymById(gymId);
        model.addAttribute("gym", gym);
        return "gyms-edit";
    }
    @PostMapping("/gyms/{gymId}/edit")
    public String updateGym(@PathVariable("gymId") Long gymId,
                             @Valid @ModelAttribute("gym") GymDto gym,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("gym", gym);
            return "gyms-edit";
        }
        gym.setId(gymId);
        gymService.updateGym(gym);
        return "redirect:/gyms";
    }

    @GetMapping("/gyms/{gymId}/delete")
    public String deleteGym(@PathVariable("gymId")Long gymId) {
        gymService.delete(gymId);
        return "redirect:/gyms";
    }
    @GetMapping("/gyms/search")
    public String searchGym(@RequestParam(value = "query") String query, Model model) {
        List<GymDto> gyms = gymService.searchGyms(query);
        model.addAttribute("gyms", gyms);
        return "gyms-list";
    }

}
