package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.DTOS.CommentDetailsDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-07-14T20:41:43+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    private UserRepository userRepository;

    public CommentMapperImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Comment commentMapperDTO(AddCommentDTO addCommentDTO) {
        if ( addCommentDTO == null ) {
            return null;
        }

        Comment commentEntity = new Comment();
        Optional<User> user = userRepository.findByEmail(addCommentDTO.getEmail());
        if (user.isPresent()){
            commentEntity.setAuthor(user.get());
            commentEntity.setEmail(user.get().getEmail());
        }else {
            commentEntity.setAuthor(null);
            commentEntity.setEmail(addCommentDTO.getEmail());
        }
        commentEntity.setMessage(addCommentDTO.getMessage());
        commentEntity.setCreated();

        return commentEntity;
    }

    @Override
    public CommentDetailsDTO commentEntityToCommentDetailDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDetailsDTO commentDetailsDTO = new CommentDetailsDTO();

        commentDetailsDTO.setAuthor(comment.getAuthor());
        commentDetailsDTO.setCreated(comment.getCreated());
        commentDetailsDTO.setMessage(comment.getMessage());

        return commentDetailsDTO;
    }
}
