package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.AddBookingDTO;
import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.mapper.BookingMapper;
import bg.softuni.HappyCats.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }


    public void addBooking(AddBookingDTO addBookingDTO) {
        Booking newBooking = bookingMapper.bookingMapperDTO(addBookingDTO);
        bookingRepository.save(newBooking);
    }
}
