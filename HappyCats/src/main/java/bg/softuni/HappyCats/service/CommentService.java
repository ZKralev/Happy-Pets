package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.mapper.CommentMapper;
import bg.softuni.HappyCats.repository.CommentRepository;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
    }


    public void addComment(AddCommentDTO addCommentDTO, HappyPetsUserDetailsService userDetails) {
        Comment newComment = commentMapper.commentMapperDTO(addCommentDTO);
        System.out.println(newComment.toString());
        commentRepository.save(newComment);

    }
}
