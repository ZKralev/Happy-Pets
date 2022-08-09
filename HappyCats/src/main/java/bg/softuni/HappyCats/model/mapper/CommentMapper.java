package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.DTOS.CommentDetailsDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "active", constant = "true")
    Comment commentMapperDTO(AddCommentDTO addCommentDTO);

    @Mapping(target = "active", constant = "true")
    CommentDetailsDTO commentEntityToCommentDetailDto(Comment comment);
}
