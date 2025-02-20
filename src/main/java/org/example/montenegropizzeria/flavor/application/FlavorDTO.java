package org.example.montenegropizzeria.flavor.application;

import java.util.List;

public class FlavorDTO {

    private String name;
    private List<Long> ingredients;

    public FlavorDTO() {
    }

    public FlavorDTO(String name, List<Long> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }
}
