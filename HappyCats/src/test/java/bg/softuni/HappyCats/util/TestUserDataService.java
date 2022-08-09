package bg.softuni.HappyCats.util;

import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.user.PetsUserDetails;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestUserDataService implements UserDetailsService {

  private final UserRepository userRepository;

  public TestUserDataService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userEntity = userRepository.findByUsername(username);
    GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" +
            userEntity.get().getUserRoles().name());
    return new PetsUserDetails(1L,
            "123123",
            username,
            "Test test",
            auth);
  }
}
