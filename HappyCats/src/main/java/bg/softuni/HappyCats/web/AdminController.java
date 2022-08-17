package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.exception.ObjectNotFoundException;
import bg.softuni.HappyCats.model.DTOS.CreateOrUpdateUserDTO;
import bg.softuni.HappyCats.service.AdminService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String allUsers(
            Model model,
            @PageableDefault(
                    sort = "username",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 20) Pageable pageable) {

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

    @GetMapping("/makeAdmin/{id}")
    public String makeUserAdmin(@PathVariable("id") Long id) {

        adminService.makeAdmin(id);

        return "index";
    }

    @GetMapping("/makeUser/{id}")
    public String makeUserUser(@PathVariable("id") Long id) {

        adminService.makeUserUser(id);

        return "index";
    }




}
