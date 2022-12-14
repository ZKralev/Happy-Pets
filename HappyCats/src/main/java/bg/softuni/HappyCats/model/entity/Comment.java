package bg.softuni.HappyCats.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime created;

    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    private User author;


    public Comment() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated() {
        System.out.println(LocalDateTime.now());
        this.created = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String text) {
        this.message = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", created=" + created +
                ", message='" + message + '\'' +
                ", author=" + author +
                '}';
    }
}

