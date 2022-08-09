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

       admin.setUsername(username);
       admin.setFullName("Admin Adminov");
       admin.setUserRoles(UserRoleEnum.ADMIN);

    return userRepository.save(admin);
  }

  public User createTestUser(String username) {

    User user = new User();
    user.setUsername(username);
    user.setFullName("Ivancho NeAdminov");
    user.setUserRoles(UserRoleEnum.USER);

    return userRepository.save(user);
  }

  public Booking createTestBooking(User testUser) {
    Booking booking = new Booking();
    booking.setEmail("ivan@example.com");
    booking.setName("ivan");
    booking.setService(Service.GROOMING);
    booking.setReservationDateTime(LocalDateTime.parse("10-17-2021 15:40:10"));

    return bookingRepository.save(booking);
  }

  public Comment createTestComment(User testAdmin) {
    Comment comment = new Comment();
        comment.setMessage("Hello");

    return commentRepository.save(comment);
  }

  public Pets createTestPet(Pets Pets) {
    Pets pet = new Pets();
    pet.setBreed("sharpei");
    pet.setKind(1);
    pet.setName("lola");
    return petsRepository.save(pet);
  }

  public void cleanUpDatabase() {
    userRepository.deleteAll();
    petsRepository.deleteAll();
    commentRepository.deleteAll();
    bookingRepository.deleteAll();
  }
}
