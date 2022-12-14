package bg.softuni.HappyCats.model.entity;

import bg.softuni.HappyCats.model.enums.PlanEnum;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany
    private List<Pets> pets;


    @Column(name="user_role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public User() {
        this.pets = new ArrayList<>();
    }


    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email, String fullName) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Pets> getPets() {
        return pets;
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
    }


    public UserRoleEnum getUserRoles() {
        return userRole;
    }

    public void setUserRoles(UserRoleEnum userRoles) {
        this.userRole = userRoles;
    }

    @ManyToOne()
    private Plan plans;


    public void setPlans(Plan plans) {
        this.plans = plans;
    }



}
