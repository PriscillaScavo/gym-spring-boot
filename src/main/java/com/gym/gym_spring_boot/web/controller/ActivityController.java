package com.gym.gym_spring_boot.web.controller;

import com.gym.gym_spring_boot.web.dto.ActivityDto;
import com.gym.gym_spring_boot.web.models.Activity;
import com.gym.gym_spring_boot.web.models.UserEntity;
import com.gym.gym_spring_boot.web.security.SecurityUtil;
import com.gym.gym_spring_boot.web.service.ActivityService;
import com.gym.gym_spring_boot.web.service.GymService;
import com.gym.gym_spring_boot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ActivityController {

    private ActivityService activityService;
    private GymService gymService;
    private UserService userService;
    @Autowired
    public ActivityController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @GetMapping("/activities")
    public String activityList(Model model) {
        UserEntity user = new UserEntity();
        List<ActivityDto> activities = activityService.findAllActivities();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("activities", activities);
        model.addAttribute("user", user);
        return "activities-list";
    }

    @GetMapping("/activities/{activityId}")
    public String viewActivities(@PathVariable("activityId")Long activityId, Model model) {
        UserEntity user = new UserEntity();
        ActivityDto activityDto = activityService.findByActivityId(activityId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("gym", activityDto.getGym());
        model.addAttribute("user", user);
        model.addAttribute("activity", activityDto);
        return "activities-detail";
    }

    @GetMapping("/activities/{gymId}/new")
    public String createActivityForm(@PathVariable("gymId") Long gymId, Model model) {
        Activity activity = new Activity();
        model.addAttribute("gymId", gymId);
        model.addAttribute("activity", activity);
        return "activities-create";
    }

    @GetMapping("/activities/{activityId}/edit")
    public String editActivityForm(@PathVariable("activityId") Long activityId, Model model) {
        ActivityDto activity = activityService.findByActivityId(activityId);
        model.addAttribute("activity", activity);
        return "activities-edit";
    }

    @PostMapping("/activities/{gymId}")
    public String createActivity(@PathVariable("gymId") Long gymId, @ModelAttribute("activity") ActivityDto activityDto,
                              BindingResult result,
                              Model model) {
        if(result.hasErrors()) {
            model.addAttribute("activity", activityDto);
            return "gyms-create";
        }
        activityService.createActivity(gymId, activityDto);
        return "redirect:/gyms/" + gymId;
    }

    @PostMapping("/activities/{activityId}/edit")
    public String updateActivity(@PathVariable("activityId") Long activityId,
                              @Valid @ModelAttribute("activity") ActivityDto activity,
                              BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("activity", activity);
            return "activities-edit";
        }
        ActivityDto activityDto = activityService.findByActivityId(activityId);
        activity.setId(activityId);
        activity.setGym(activityDto.getGym());
        activityService.updateActivity(activity);
        return "redirect:/activities";
    }

    @GetMapping("/activities/{activityId}/delete")
    public String deleteActivity(@PathVariable("activityId") long activityId) {
        activityService.deleteActivity(activityId);
        return "redirect:/activities";
    }

}
