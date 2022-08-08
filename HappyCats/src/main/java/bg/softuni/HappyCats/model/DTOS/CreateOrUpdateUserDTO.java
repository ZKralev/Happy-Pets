package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.enums.Plan;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;
import java.util.List;

public class CreateOrUpdateUserDTO {

  private long id;

  private String username;


  private String email;

  private String fullName;

  private List<Pets> pets;

  private Plan plan;

  private UserRoleEnum userRole;

  public CreateOrUpdateUserDTO() {
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

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public UserRoleEnum getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRoleEnum userRole) {
    this.userRole = userRole;
  }
}
