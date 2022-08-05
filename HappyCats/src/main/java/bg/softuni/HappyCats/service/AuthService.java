package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;
import bg.softuni.HappyCats.model.mapper.UserMapper;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Locale;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final HappyPetsUserDetailsService userDetailsService;
    private final EmailService emailService;

    @Autowired
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       HappyPetsUserDetailsService userDetailsService,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
    }

    public void registerAndLogin(UserRegistrationDTO userRegisterDTO, Locale preferredLocale) {

        User newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        newUser.setUserRoles(UserRoleEnum.USER);
        newUser.setUsername(userRegisterDTO.getUsername());

        this.userRepository.save(newUser);
        login(newUser);
        emailService.sendRegistrationEmail(newUser.getEmail(),
                newUser.getFullName(), preferredLocale);
    }


    private void login(User userEntity) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }


}
