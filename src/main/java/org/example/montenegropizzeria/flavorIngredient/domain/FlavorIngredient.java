package org.example.montenegropizzeria.flavorIngredient.domain;

import jakarta.persistence.*;
import org.example.montenegropizzeria.flavor.domain.Flavor;
import org.example.montenegropizzeria.ingredient.domain.Ingredient;

@Entity
@Table(name = "flavor_ingredient")
public class FlavorIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "flavor_id", nullable = false)
    private Flavor flavor;

    public FlavorIngredient() {
    }

    public FlavorIngredient(Long id, Ingredient ingredient, Flavor flavor) {
        this.id = id;
        this.ingredient = ingredient;
        this.flavor = flavor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }
}
