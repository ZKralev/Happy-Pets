package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;
import bg.softuni.HappyCats.model.mapper.UserMapper;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public AdminService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Page<UserDetailDTO> getAllUsers(Pageable pageable) {
        return userRepository.
                findAll(pageable).
                map(userMapper::userEntityToUserDetailDto);

    }



    public Optional<UserDetailDTO> findUserByID(Long userID) {
        return userRepository.
                findById(userID).
                map(userMapper::userEntityToUserDetailDto);
    }



    public void makeAdmin(Long id) {

        Optional<User> user = userRepository.findById(id);

        user.ifPresent(value -> value.setUserRoles(UserRoleEnum.ADMIN));

        userRepository.save(user.get());
    }

    public void makeUserUser(Long id) {

        Optional<User> user = userRepository.findById(id);

        user.ifPresent(value -> value.setUserRoles(UserRoleEnum.USER));

        userRepository.save(user.get());
    }
}
