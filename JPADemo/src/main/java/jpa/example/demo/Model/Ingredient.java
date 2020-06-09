package jpa.example.demo.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id                                                                    //Generated value for at den automatisk generer værdi for ID -
    @GeneratedValue(strategy = GenerationType.IDENTITY)                    // IDENTITY siger at den er unik kun for denne type hierarki.
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    private Recipe recipe;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
