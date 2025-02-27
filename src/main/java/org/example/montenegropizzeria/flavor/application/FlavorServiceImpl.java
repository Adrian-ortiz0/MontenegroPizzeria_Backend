package org.example.montenegropizzeria.flavor.application;

import org.example.montenegropizzeria.flavor.domain.Flavor;
import org.example.montenegropizzeria.flavor.domain.FlavorRepository;
import org.example.montenegropizzeria.ingredient.domain.Ingredient;
import org.example.montenegropizzeria.ingredient.domain.IngredientRepository;
import org.example.montenegropizzeria.product.domain.Product;
import org.example.montenegropizzeria.product.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlavorServiceImpl implements FlavorService {

    private final FlavorRepository flavorRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FlavorServiceImpl(FlavorRepository flavorRepository,
                             IngredientRepository ingredientRepository,
                             ProductRepository productRepository) {
        this.flavorRepository = flavorRepository;
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Flavor> findById(Long id) {
        return flavorRepository.findById(id);
    }

    @Override
    public List<Flavor> listAllFlavors() {
        return flavorRepository.findAll();
    }

    @Override
    public Flavor saveFlavor(FlavorDTO flavorDTO) {
        Flavor flavor = new Flavor();
        flavor.setName(flavorDTO.getName());
        flavor.setPrice(flavorDTO.getPrice());
        List<Ingredient> ingredients = ingredientRepository.findAllById(flavorDTO.getIngredients());

        flavor.setIngredients(ingredients);
        return flavorRepository.save(flavor);
    }

    @Override
    public void deleteFlavor(Long id) {
        flavorRepository.deleteById(id);
    }

    public Optional<Flavor> updateFlavor(Long id, FlavorDTO flavorDTO) {
        Optional<Flavor> flavorDb = flavorRepository.findById(id);
        if (flavorDb.isPresent()) {
            Flavor flavor = flavorDb.get();
            flavor.setPrice(flavorDTO.getPrice());
            flavor.setName(flavorDTO.getName());

            List<Ingredient> ingredients = ingredientRepository.findAllById(flavorDTO.getIngredients());
            flavor.setIngredients(ingredients);

            return Optional.of(flavorRepository.save(flavor));
        }
        return Optional.empty();
    }
}
