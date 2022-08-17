package bg.softuni.HappyCats.model.entity;

import bg.softuni.HappyCats.model.enums.PlanEnum;

import javax.persistence.*;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private PlanEnum planEnum;

    private float price;



    @Transient
    private User user;

    public Plan() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
