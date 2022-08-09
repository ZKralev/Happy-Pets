package bg.softuni.HappyCats.service;


import bg.softuni.HappyCats.model.DTOS.CommentDetailsDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.mapper.CommentMapper;
import bg.softuni.HappyCats.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicePageService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentsMapper;

    public ServicePageService(CommentRepository commentRepository, CommentMapper commentsMapper) {
        this.commentRepository = commentRepository;
        this.commentsMapper = commentsMapper;
    }

    public Page<CommentDetailsDTO> getAllComments(Pageable pageable) {
        return commentRepository.
                findAll(pageable).
                map(commentsMapper::commentEntityToCommentDetailDto);

    }
}
