package org.example.montenegropizzeria.ingredient.application;

import org.example.montenegropizzeria.ingredient.domain.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    List<Ingredient> findAllIngredients();
    Optional<Ingredient> findIngredientById(Long id);
    Ingredient saveIngredient(Ingredient ingredient);
    void deleteIngredient(Long id);
}
