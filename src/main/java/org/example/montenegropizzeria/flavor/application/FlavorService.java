package org.example.montenegropizzeria.flavor.application;

import org.example.montenegropizzeria.flavor.domain.Flavor;

import java.util.List;
import java.util.Optional;

public interface FlavorService {
    Optional<Flavor> findById(Long id);
    List<Flavor> listAllFlavors();
    Flavor saveFlavor(Flavor flavor);
    void deleteFlavor(Long id);
}
