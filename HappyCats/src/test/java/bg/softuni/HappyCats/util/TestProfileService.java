package bg.softuni.HappyCats.util;

import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.repository.UserRepository;
import bg.softuni.HappyCats.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class TestProfileService extends ProfileService {

  private final UserRepository userRepository;


  public TestProfileService(UserRepository userRepository, UserRepository userRepository1) {
    super(userRepository);
    this.userRepository = userRepository1;
  }

  @Override
  public void changeUsername(UserDetailDTO userModel) {
    super.changeUsername(userModel);
  }
}
