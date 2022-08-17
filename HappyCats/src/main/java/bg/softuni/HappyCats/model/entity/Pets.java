package bg.softuni.HappyCats.model.entity;

import bg.softuni.HappyCats.model.enums.PetsKind;


import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "pets")
public class Pets {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    @Positive
    private int age;
    @Enumerated(EnumType.STRING)
    private PetsKind kind;

    private String breed;
    @ManyToOne
    private User owner;

    public Pets() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetsKind getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = PetsKind.values()[kind];
    }

    public void setOwner(User owner) {
        this.owner = owner;
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


}
