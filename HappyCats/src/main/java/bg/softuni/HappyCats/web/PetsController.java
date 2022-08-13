package bg.softuni.HappyCats.web;
import bg.softuni.HappyCats.model.DTOS.AddPetsDTO;
import bg.softuni.HappyCats.service.HappyPetsUserDetailsService;
import bg.softuni.HappyCats.service.PetsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PetsController {

    private final PetsService petsService;

    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping("/pets")
    public String addPets(Model model) {
        if (!model.containsAttribute("addPetsModel")) {
            model.addAttribute("addPetsModel", new AddPetsDTO());
        }
        return "add-pets";
    }

    @PostMapping("/pets")
    public String addPets(@Valid AddPetsDTO addPetsModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal HappyPetsUserDetailsService userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPetsModel", addPetsModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPetsModel",
                    bindingResult);
            return "redirect:/pets";
        }

        petsService.addPets(addPetsModel, userDetails);

        return "redirect:/pets";
    }


    @GetMapping("/all-pets")
    public String allUsers(
            Model model,
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 5) Pageable pageable) {

        model.addAttribute("pets", petsService.getAllPets(pageable));

        return "all-pets";
    }
}
