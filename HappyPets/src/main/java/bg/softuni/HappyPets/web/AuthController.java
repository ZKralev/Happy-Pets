package bg.softuni.HappyPets.web;

import bg.softuni.HappyPets.model.DTOS.AddBookingDTO;
import bg.softuni.HappyPets.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyPets.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;
    private LocaleResolver localeResolver;

    public AuthController(AuthService authService, LocaleResolver localeResolver) {
        this.authService = authService;
        this.localeResolver = localeResolver;
    }

    @ModelAttribute("addBookingDTO")
    public AddBookingDTO addBookingDTO() {
        return new AddBookingDTO();
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/register";
        }

        this.authService.registerAndLogin(
                userModel,
                localeResolver.resolveLocale(request));


        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }

}
