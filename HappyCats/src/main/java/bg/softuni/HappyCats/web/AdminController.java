package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.exception.ObjectNotFoundException;
import bg.softuni.HappyCats.model.DTOS.CreateOrUpdateUserDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyCats.service.AdminService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String allUsers(
            Model model,
            @PageableDefault(
                    sort = "username",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 5) Pageable pageable) {

        model.addAttribute("users", adminService.getAllUsers(pageable));

        return "admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public String getUserDetail(@PathVariable("id") Long id,
                                Model model) {

        var userDto =
                adminService.findUserByID(id).
                        orElseThrow(() -> new ObjectNotFoundException("User with ID " +
                                id + " not found!"));

        model.addAttribute("userDto", userDto);

        return "details";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        var user = adminService.getUserEditDetails(id).
                orElseThrow(() -> new ObjectNotFoundException("User with ID "+ id + "not found"));

        model.addAttribute("user", user);

        return "details";
    }

    @PostMapping("/user/{id}")
    public String changeUserRoles(@PathVariable("id") Long id,Model model) {

        CreateOrUpdateUserDTO userDto =
                adminService.getUserEditDetails(id).
                        orElseThrow(() -> new ObjectNotFoundException("User with ID " +
                                id + " not found!"));

        model.addAttribute("userDto", userDto);


        this.adminService.changeUserRole(userDto);


        return "redirect:/admin";
    }



}
