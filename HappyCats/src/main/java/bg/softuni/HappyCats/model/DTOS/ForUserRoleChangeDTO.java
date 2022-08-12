package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.enums.UserRoleEnum;

public class ForUserRoleChangeDTO {

    private UserRoleEnum userRole;

    public ForUserRoleChangeDTO() {
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        if(userRole == 0){
            this.userRole = UserRoleEnum.ADMIN;
        }else if(userRole == 1){
            this.userRole = UserRoleEnum.USER;
        }
    }
}
