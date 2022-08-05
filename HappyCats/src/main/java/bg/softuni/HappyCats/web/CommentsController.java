package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.service.CommentService;
import bg.softuni.HappyCats.service.HappyPetsUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CommentsController {

    private CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment")
    public String addComment(Model model) {
        if (!model.containsAttribute("addCommentDTO")) {
            model.addAttribute("addCommentDTO", new AddCommentDTO());
        }
        return "add-comment";
    }

    @PostMapping("/comment")
    public String addComment(@Valid AddCommentDTO addCommentDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal HappyPetsUserDetailsService userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addCommentDTO", addCommentDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addCommentDTO",
                    bindingResult);
            return "redirect:/comment";
        }
        commentService.addComment(addCommentDTO, userDetails);

        return "redirect:/index";
    }
}
