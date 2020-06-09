package jpa.example.demo.Model;

import javax.persistence.*;

@Entity
public class Notes {

    @Id                                                                    //Generated value for at den automatisk generer værdi for ID -
    @GeneratedValue(strategy = GenerationType.IDENTITY)                    // IDENTITY siger at den er unik kun for denne type hierarki.
    private Long id; //Dette er en anden måde at stille koden op på.

    private String Description;

    @OneToOne(cascade = CascadeType.ALL)
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
