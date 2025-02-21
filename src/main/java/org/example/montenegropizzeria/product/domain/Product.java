package org.example.montenegropizzeria.product.domain;
import jakarta.persistence.*;
import org.example.montenegropizzeria.flavor.domain.Flavor;
import org.example.montenegropizzeria.size.domain.Size;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Flavor> flavors;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Size> sizes;

    private String image;

    public Product() {
    }

    public Product(Long id, String name, List<Flavor> flavors, List<Size> sizes, String image) {
        this.id = id;
        this.name = name;
        this.flavors = flavors;
        this.sizes = sizes;
        this.image = image;
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

    public List<Flavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<Flavor> flavors) {
        this.flavors = flavors;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
