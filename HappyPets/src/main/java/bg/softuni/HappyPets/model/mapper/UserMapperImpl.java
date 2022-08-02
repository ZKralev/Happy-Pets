package bg.softuni.HappyPets.model.mapper;

import bg.softuni.HappyPets.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyPets.model.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-07-14T20:41:43+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUserEntity(UserRegistrationDTO registerDTO) {
        if (registerDTO == null) {
            return null;
        }

        User userEntity = new User();

        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPassword(registerDTO.getPassword());
        userEntity.setFullName(registerDTO.getFullname());


        return userEntity;
    }
}
