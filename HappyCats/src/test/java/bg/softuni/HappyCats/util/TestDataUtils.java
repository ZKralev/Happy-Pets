package bg.softuni.HappyCats.util;


import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.Service;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;
import bg.softuni.HappyCats.repository.BookingRepository;
import bg.softuni.HappyCats.repository.CommentRepository;
import bg.softuni.HappyCats.repository.PetsRepository;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class TestDataUtils {

  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;
  private final CommentRepository commentRepository;
  private final PetsRepository petsRepository;

  public TestDataUtils(UserRepository userRepository, BookingRepository bookingRepository, CommentRepository commentRepository, PetsRepository petsRepository) {
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
    this.commentRepository = commentRepository;
    this.petsRepository = petsRepository;
  }


  public User createTestAdmin(String username) {

    User admin = new User();

      admin.setEmail("admin@example.com");
      admin.setPassword("4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea");
       admin.setUsername(username);
       admin.setFullName("Admin Adminov");
       admin.setUserRoles(UserRoleEnum.ADMIN);

    return userRepository.save(admin);
  }

  public User createTestUser(String username) {

    User user = new User();
    user.setEmail("admin2@example.com");
    user.setPassword("4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea");
    user.setUsername(username);
    user.setFullName("Admin NeAdminov");
    user.setUserRoles(UserRoleEnum.USER);

    return userRepository.save(user);
  }

  public Booking createTestBooking(User testUser) {
    Booking booking = new Booking();
    booking.setEmail(testUser.getEmail());
    booking.setName("ivan");
    booking.setService(Service.GROOMING);

    return bookingRepository.save(booking);
  }

//  public Comment createTestComment(User testAdmin) {
//    Comment comment = new Comment();
//    comment.setAuthor(testAdmin);
//    comment.setMessage("Hello");
//
//    return commentRepository.save(comment);
//  }

  public Pets createTestPet(User owner) {
    Pets pet = new Pets();
    pet.setBreed("sharpei");
    pet.setKind(1);
    pet.setName("lola");
    pet.setOwner(owner);
    return petsRepository.save(pet);
  }

  public void cleanUpDatabase() {
    userRepository.deleteAll();
    petsRepository.deleteAll();
    commentRepository.deleteAll();
    bookingRepository.deleteAll();
  }
}
