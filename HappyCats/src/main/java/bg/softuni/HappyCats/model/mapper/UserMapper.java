package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.CreateOrUpdateUserDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyCats.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "active", constant = "true")
  User userDtoToUserEntity(UserRegistrationDTO registerDTO);

  CreateOrUpdateUserDTO offerEntityToCreateOrUpdateUserDtoTo(User user);

  @Mapping(target = "active", constant = "true")
  UserDetailDTO userEntityToUserDetailDto(User user);
}
