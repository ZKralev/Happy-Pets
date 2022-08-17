package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.DTOS.AddPlanDTO;
import bg.softuni.HappyCats.model.enums.PlanEnum;
import bg.softuni.HappyCats.repository.UserRepository;
import bg.softuni.HappyCats.service.PlanService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PlanController {

    private final UserRepository userRepository;

    private final PlanService planService;

    public PlanController(UserRepository userRepository, PlanService planService) {
        this.userRepository = userRepository;
        this.planService = planService;
    }

    @GetMapping("/addPlanBasic")
    public String addPlanBasic() {

        planService.addPlanBasic();

        return "index";
    }

    @GetMapping("/addPlanStandard")
    public String addPlanStandard() {

        planService.addPlanStandard();

        return "index";
    }

    @GetMapping("/addPlanPremium")
    public String addPlanPremium() {

        planService.addPlanPremium();

        return "index";
    }

}
