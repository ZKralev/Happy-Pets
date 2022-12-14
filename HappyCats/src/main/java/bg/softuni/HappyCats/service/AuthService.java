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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Locale;
import java.util.Optional;


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

        Optional<User> userExists = userRepository.findByUsername(userRegisterDTO.getUsername());
        if(userExists.isPresent()){
            throw new RuntimeException();
        }

        User newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser);
        emailService.sendRegistrationEmail(newUser.getEmail(),
                newUser.getUsername(), preferredLocale);
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


    public User getUserByUsername(String pesho) {
        return userRepository.findByUsername(pesho).get();
    }
}
