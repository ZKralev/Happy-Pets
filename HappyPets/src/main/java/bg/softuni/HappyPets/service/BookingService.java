package bg.softuni.HappyPets.service;

import bg.softuni.HappyPets.model.DTOS.AddBookingDTO;
import bg.softuni.HappyPets.model.entity.Booking;
import bg.softuni.HappyPets.model.mapper.BookingMapper;
import bg.softuni.HappyPets.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }


    public void addBooking(AddBookingDTO addBookingDTO) {
        Booking newBooking = bookingMapper.bookingMapperDTO(addBookingDTO);
        bookingRepository.save(newBooking);
    }
}
