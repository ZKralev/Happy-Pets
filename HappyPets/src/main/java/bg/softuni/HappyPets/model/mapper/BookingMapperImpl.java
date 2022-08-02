package bg.softuni.HappyPets.model.mapper;

import bg.softuni.HappyPets.model.DTOS.AddBookingDTO;
import bg.softuni.HappyPets.model.entity.Booking;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-07-14T20:41:43+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public Booking bookingMapperDTO(AddBookingDTO addBookingDTO) {
        if (addBookingDTO == null) {
            return null;
        }

        Booking bookingEntity = new Booking();

        bookingEntity.setName(addBookingDTO.getName());
        bookingEntity.setEmail(addBookingDTO.getEmail());
        bookingEntity.setReservationDate(addBookingDTO.getReservationDate());
        bookingEntity.setReservationTime(addBookingDTO.getReservationTime());
        bookingEntity.setService(addBookingDTO.getService());

        return bookingEntity;
    }
}
