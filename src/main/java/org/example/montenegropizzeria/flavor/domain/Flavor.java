package org.example.montenegropizzeria.flavor.domain;
import jakarta.persistence.*;
import org.example.montenegropizzeria.flavorIngredient.domain.FlavorIngredient;
import org.example.montenegropizzeria.product.domain.Product;

import java.util.List;

@Entity
@Table(name = "flavors")
public class Flavor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "flavor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FlavorIngredient> ingredients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Flavor() {
    }

    public Flavor(Long id, String name, List<FlavorIngredient> ingredients, Product product) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.product = product;
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

    public List<FlavorIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<FlavorIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
