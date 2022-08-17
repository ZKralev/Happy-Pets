package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.PlanEnum;


public class AddPlanDTO {

    private PlanEnum planEnum;

    private float price;

    private User user;

    public AddPlanDTO() {
    }

    public PlanEnum getPlanEnum() {
        return planEnum;
    }

    public void setPlanEnum(PlanEnum planEnum) {
        this.planEnum = planEnum;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
