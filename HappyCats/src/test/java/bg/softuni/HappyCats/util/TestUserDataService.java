package bg.softuni.HappyCats.util;

import bg.softuni.HappyCats.model.user.PetsUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TestUserDataService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new PetsUserDetails(1L,
        "123123",
        username,
        "Test test");
  }
}
