package org.example.montenegropizzeria.ingredient.domain;

import jakarta.persistence.*;
import org.example.montenegropizzeria.flavor.domain.Flavor;
import org.example.montenegropizzeria.flavorIngredient.domain.FlavorIngredient;

import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FlavorIngredient> flavors;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, List<FlavorIngredient> flavors) {
        this.id = id;
        this.name = name;
        this.flavors = flavors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FlavorIngredient> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<FlavorIngredient> flavors) {
        this.flavors = flavors;
    }
}
