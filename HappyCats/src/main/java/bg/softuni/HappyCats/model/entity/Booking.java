package bg.softuni.HappyCats.model.entity;

import bg.softuni.HappyCats.model.enums.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static bg.softuni.HappyCats.model.enums.Service.valueOf;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String email;

    @Column(name = "reservation_date_time")
    private LocalDateTime reservationDateTime;

    @Enumerated(EnumType.STRING)
    private Service service;


    public Booking() {
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

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(int service) {
        this.service = Service.values()[service];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
