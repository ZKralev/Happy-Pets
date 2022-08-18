package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.repository.UserRepository;
import bg.softuni.HappyCats.service.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PlanController {


    private final PlanService planService;

    public PlanController(PlanService planService) {
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
