package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.mapper.CommentMapper;
import bg.softuni.HappyCats.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }


    public void addComment(AddCommentDTO addCommentDTO) {
        Comment newComment = commentMapper.commentMapperDTO(addCommentDTO);
        System.out.println(newComment.toString());
        commentRepository.save(newComment);

    }
}
