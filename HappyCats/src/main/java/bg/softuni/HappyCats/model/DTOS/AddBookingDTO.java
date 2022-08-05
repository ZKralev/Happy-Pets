package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.enums.Service;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class AddBookingDTO {

    private String name;

    private String email;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime reservationDateTime;

    private int service;

    public AddBookingDTO() {
    }

    public String getName() {
        return name;
    }

    public AddBookingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AddBookingDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public int getService() {
        return service;
    }

    public AddBookingDTO setService(int service) {
        this.service = service;
        return this;
    }
}
