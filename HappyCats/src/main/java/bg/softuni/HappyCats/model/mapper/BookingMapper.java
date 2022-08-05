package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddBookingDTO;
import bg.softuni.HappyCats.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

  @Mapping(target = "active", constant = "true")
  Booking bookingMapperDTO(AddBookingDTO addBookingDTO);
}
