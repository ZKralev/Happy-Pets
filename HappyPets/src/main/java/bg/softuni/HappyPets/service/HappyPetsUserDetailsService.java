package bg.softuni.HappyPets.service;

import bg.softuni.HappyPets.model.entity.User;
import bg.softuni.HappyPets.model.user.PetsUserDetails;
import bg.softuni.HappyPets.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class HappyPetsUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public HappyPetsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository.
                findByUsername(username).
                map(this::mapIt).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails mapIt(User userEntity) {

        GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" +
                userEntity.getUserRoles().name());

        return new PetsUserDetails(
                userEntity.getId(),
                userEntity.getPassword(),
                userEntity.getUsername(),
                userEntity.getFullName(),
                auth);
    }


}
