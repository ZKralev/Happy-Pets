package bg.softuni.HappyPets.model.mapper;

import bg.softuni.HappyPets.model.DTOS.AddBookingDTO;
import bg.softuni.HappyPets.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "active", constant = "true")
    Booking bookingMapperDTO(AddBookingDTO addBookingDTO);
}
