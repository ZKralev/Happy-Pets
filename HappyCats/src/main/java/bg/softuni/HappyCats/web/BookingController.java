package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.DTOS.AddBookingDTO;
import bg.softuni.HappyCats.service.BookingService;
import bg.softuni.HappyCats.service.HappyPetsUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public String addBooking( Model model) {
        if (!model.containsAttribute("addBookingModel")) {
            model.addAttribute("addBookingModel", new AddBookingDTO());
        }
        return "booking";
    }

    @PostMapping("/booking")
    public String addBooking(@Valid AddBookingDTO addBookingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal HappyPetsUserDetailsService userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addBookingModel", addBookingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBookingModel",
                    bindingResult);
            return "redirect:/booking";
        }

        bookingService.addBooking(addBookingModel);

        return "redirect:/booking";
    }

}
