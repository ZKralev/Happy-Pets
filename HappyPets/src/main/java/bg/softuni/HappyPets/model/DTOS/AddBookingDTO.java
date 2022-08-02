package bg.softuni.HappyPets.model.DTOS;

import bg.softuni.HappyPets.model.enums.Service;

import java.sql.Time;
import java.util.Date;

public class AddBookingDTO {

    private String name;

    private String email;

    private Date reservationDate;

    private Time reservationTime;

    private Service service;

    public AddBookingDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Time getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Time reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
