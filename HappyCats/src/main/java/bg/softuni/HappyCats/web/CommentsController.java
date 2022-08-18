package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentsController {

    private CommentService commentService;

    @Autowired
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
    public String addComment(AddCommentDTO addCommentDTO) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();


        commentService.addComment(addCommentDTO);

        return "redirect:/index";
    }
}
