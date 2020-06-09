package jpa.example.demo.Model;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {

    @Id                                                                    //Generated value for at den automatisk generer værdi for ID -
    @GeneratedValue(strategy = GenerationType.IDENTITY)                    // IDENTITY siger at den er unik kun for denne type hierarki.
    private Long id;

    private String description;

    @OneToOne
    private Ingredient ingredient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
