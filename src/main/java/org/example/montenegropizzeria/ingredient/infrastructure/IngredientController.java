package org.example.montenegropizzeria.ingredient.infrastructure;

import org.example.montenegropizzeria.ingredient.application.IngredientService;
import org.example.montenegropizzeria.ingredient.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> findAllIngredients() {
        return ingredientService.findAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findIngredientById(@PathVariable Long id) {
        Optional<Ingredient> ingredient = ingredientService.findIngredientById(id);
        if (ingredient.isPresent()) {
            return ResponseEntity.ok(ingredient.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        Optional<Ingredient> ingredientDb = ingredientService.findIngredientById(id);
        if (ingredientDb.isPresent()) {
            ingredientDb.get().setName(ingredient.getName());
            ingredientService.saveIngredient(ingredientDb.get());
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable Long id) {
        Optional<Ingredient> ingredient = ingredientService.findIngredientById(id);
        if (ingredient.isPresent()) {
            ingredientService.deleteIngredient(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
