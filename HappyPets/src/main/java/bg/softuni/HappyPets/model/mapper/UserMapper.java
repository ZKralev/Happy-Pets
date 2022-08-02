package bg.softuni.HappyPets.model.mapper;

import bg.softuni.HappyPets.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyPets.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    User userDtoToUserEntity(UserRegistrationDTO registerDTO);
}
