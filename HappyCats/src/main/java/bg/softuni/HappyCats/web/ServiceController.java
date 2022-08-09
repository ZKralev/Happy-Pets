package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.service.ServicePageService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {

    private final ServicePageService serviceService;

    public ServiceController(ServicePageService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/service")
    public String allComments(
            Model model,
            @PageableDefault(
                    sort = "created",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 5) Pageable pageable) {

        model.addAttribute("comments", serviceService.getAllComments(pageable));

        return "service";
    }
}
