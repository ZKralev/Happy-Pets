package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddBookingDTO;
import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.Optional;


@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-07-14T20:41:43+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    private UserRepository userRepository;

    public BookingMapperImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Booking bookingMapperDTO(AddBookingDTO addBookingDTO) {
        if ( addBookingDTO == null ) {
            return null;
        }

        Booking bookingEntity = new Booking();

        bookingEntity.setName(addBookingDTO.getName());
        bookingEntity.setEmail(addBookingDTO.getEmail());
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> userByLogged = userRepository.findByUsername(username);
        Optional<User> userByEmail = userRepository.findByEmail(addBookingDTO.getEmail());
        if(userByLogged.isPresent()){
            bookingEntity.setUser(userByLogged.get());
        }else if(userByEmail.isPresent()){
            bookingEntity.setUser(userByEmail.get());
        }else{
            bookingEntity.setUser(null);
        }
        bookingEntity.setReservationDateTime(addBookingDTO.getReservationDateTime());
        bookingEntity.setService(addBookingDTO.getService());

        return bookingEntity;
    }
}
