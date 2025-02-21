package org.example.montenegropizzeria.flavor.domain;
import jakarta.persistence.*;
import org.example.montenegropizzeria.ingredient.domain.Ingredient;
import org.example.montenegropizzeria.product.domain.Product;

import java.util.List;

@Entity
@Table(name = "flavors")
public class Flavor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Ingredient.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "flavor_ingredient",
            joinColumns = @JoinColumn(name = "flavor_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Flavor() {
    }

    public Flavor(Long id, String name, double price, List<Ingredient> ingredients, Product product) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
