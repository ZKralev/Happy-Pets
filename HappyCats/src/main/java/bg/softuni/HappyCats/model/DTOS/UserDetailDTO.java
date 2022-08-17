package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.enums.PlanEnum;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;

import java.util.List;

public class UserDetailDTO {


    private long id;

    private String username;


    private String email;

    private String fullName;

    private List<Pets> pets;

    private PlanEnum planEnum;

    private UserRoleEnum userRole;

    public UserDetailDTO() {
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

    public PlanEnum getPlan() {
        return planEnum;
    }

    public void setPlan(PlanEnum planEnum) {
        this.planEnum = planEnum;
    }

    public String getUserRole() {
        return userRole.toString();
    }


    public void setUserRole(String userRole) {
        if (userRole.equals("0")){
            this.userRole = UserRoleEnum.ADMIN;
        }
        if (userRole.equals("1")){
            this.userRole = UserRoleEnum.USER;
        }
    }
}
