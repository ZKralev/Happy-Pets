package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.exception.ObjectNotFoundException;
import bg.softuni.HappyCats.service.AdminService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


@Controller
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

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

    @GetMapping("/admin/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        var user = adminService.getUserEditDetails(id).
                orElseThrow(() -> new ObjectNotFoundException("User with ID "+ id + "not found"));

        model.addAttribute("user", user);

        return "details";
    }



    @GetMapping("/admin/{id}")
    public String getUserDetail(@PathVariable("id") Long id,
                                 Model model) {

        var userDto =
                adminService.findUserByID(id).
                        orElseThrow(() -> new ObjectNotFoundException("User with ID " +
                                id + " not found!"));

        model.addAttribute("userDto", userDto);

        return "details";
    }
}
