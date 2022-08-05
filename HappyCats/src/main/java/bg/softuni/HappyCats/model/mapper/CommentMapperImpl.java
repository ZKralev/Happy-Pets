package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
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
        commentEntity.setAuthor(user.get());
        commentEntity.setMessage(addCommentDTO.getMessage());
        commentEntity.setCreated();

        return commentEntity;
    }
}
