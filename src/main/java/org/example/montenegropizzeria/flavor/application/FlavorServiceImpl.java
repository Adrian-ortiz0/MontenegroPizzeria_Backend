package org.example.montenegropizzeria.flavor.application;

import org.example.montenegropizzeria.flavor.domain.Flavor;
import org.example.montenegropizzeria.flavor.domain.FlavorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlavorServiceImpl implements FlavorService {

    private final FlavorRepository flavorRepository;

    public FlavorServiceImpl(FlavorRepository flavorRepository) {
        this.flavorRepository = flavorRepository;
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
    public Flavor saveFlavor(Flavor flavor) {
        return flavorRepository.save(flavor);
    }

    @Override
    public void deleteFlavor(Long id) {
        flavorRepository.deleteById(id);
    }
}
