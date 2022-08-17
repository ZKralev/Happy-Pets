package bg.softuni.HappyCats.model.mapper;

import javax.annotation.processing.Generated;

import bg.softuni.HappyCats.model.DTOS.CreateOrUpdateUserDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-07-14T20:41:43+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUserEntity(UserRegistrationDTO registerDTO) {
        if ( registerDTO == null ) {
            return null;
        }

        User userEntity = new User();

        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setEmail( registerDTO.getEmail() );
        userEntity.setPassword( registerDTO.getPassword() );
        userEntity.setFullName( registerDTO.getFullname() );
        userEntity.setUserRoles(UserRoleEnum.USER);


        return userEntity;
    }

    @Override
    public CreateOrUpdateUserDTO userEntityToCreateOrUpdateUserDtoTo(User user) {
        if ( user == null ) {
            return null;
        }

        CreateOrUpdateUserDTO createOrUpdateUserDTO = new CreateOrUpdateUserDTO();

        createOrUpdateUserDTO.setId(user.getId());
        createOrUpdateUserDTO.setUsername(user.getUsername());
        createOrUpdateUserDTO.setFullName(user.getFullName());
        createOrUpdateUserDTO.setEmail(user.getEmail());
        createOrUpdateUserDTO.setPets(user.getPets());
        String role;
        if(user.getUserRoles() == UserRoleEnum.ADMIN){
            role = "0";
        }else{
            role = "1";
        }
        createOrUpdateUserDTO.setUserRole(role);

        return createOrUpdateUserDTO;
    }

    @Override
    public UserDetailDTO userEntityToUserDetailDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetailDTO userDetailDTO = new UserDetailDTO();

        userDetailDTO.setId(user.getId());
        userDetailDTO.setUsername(user.getUsername());
        userDetailDTO.setFullName(user.getFullName());
        userDetailDTO.setEmail(user.getEmail());
        userDetailDTO.setPets(user.getPets());
        String role;
        if(user.getUserRoles() == UserRoleEnum.ADMIN){
            role = "0";
        }else{
            role = "1";
        }

        userDetailDTO.setUserRole(role);

        return userDetailDTO;
    }


}
