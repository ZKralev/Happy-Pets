package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        UserDetailDTO userDto = profileService.getUserInfo();

        model.addAttribute("userDto", userDto);

        return "profile";
    }

    @PostMapping("/changeUsername")
    public String changeUsername(@Valid UserDetailDTO userDto) {

        profileService.changeUsername(userDto);

        return "index";
    }
}
