package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.PetsKind;


public class PetsDTO {

    private String name;
    private int age;
    private PetsKind kind;
    private String breed;
    private User owner;

    public PetsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PetsKind getKind() {
        return kind;
    }

    public void setKind(PetsKind kind) {
        this.kind = kind;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
