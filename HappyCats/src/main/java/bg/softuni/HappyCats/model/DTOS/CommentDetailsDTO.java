package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.entity.User;

import java.time.LocalDateTime;

public class CommentDetailsDTO {


    private LocalDateTime created;

    private String message;

    private User author;

    public CommentDetailsDTO() {
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
