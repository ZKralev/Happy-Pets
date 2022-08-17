package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.repository.UserRepository;
import bg.softuni.HappyCats.service.ProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ProfileController {

    private ProfileService profileService;
    private UserRepository userRepository;

    public ProfileController(ProfileService profileService, UserRepository userRepository) {
        this.profileService = profileService;
        this.userRepository = userRepository;
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
