package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.entity.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class AddCommentDTO {


    private String message;

    private String email;

    public AddCommentDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
