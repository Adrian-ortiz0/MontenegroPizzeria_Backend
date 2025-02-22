package org.example.montenegropizzeria.flavor.application;

import java.util.List;

public class FlavorDTO {

    private String name;
    private double price;
    private List<Long> ingredients;

    public FlavorDTO() {
    }

    public FlavorDTO(String name, double price, List<Long> ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }


}
