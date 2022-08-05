package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.validation.FieldMatch;
import bg.softuni.HappyCats.model.validation.UniqueUserEmail;
import javax.validation.constraints.*;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class UserRegistrationDTO {
    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;


    @Size(min = 5, max = 20)
    private String fullname;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
    @UniqueUserEmail(message = "User email should be unique.")
    private String email;


    @NotEmpty
    @Size(min = 5, max = 20)
    private String password;


    private String confirmPassword;


    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String username, String fullname, String email, String password, String confirmPassword) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;

    }

    public String getFullname() {
        return fullname;
    }

    public UserRegistrationDTO setFullname(String fullName) {
        this.fullname = fullName;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
