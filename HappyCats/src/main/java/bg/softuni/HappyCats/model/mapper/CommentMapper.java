package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddBookingDTO;
import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "active", constant = "true")
    Comment commentMapperDTO(AddCommentDTO addCommentDTO);
}
