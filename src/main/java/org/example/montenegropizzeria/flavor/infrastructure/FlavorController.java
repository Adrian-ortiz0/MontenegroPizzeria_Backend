package org.example.montenegropizzeria.flavor.infrastructure;

import org.example.montenegropizzeria.flavor.application.FlavorService;
import org.example.montenegropizzeria.flavor.domain.Flavor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/flavor")
public class FlavorController {

    private FlavorService flavorService;

    @Autowired
    public FlavorController(FlavorService flavorService) {
        this.flavorService = flavorService;
    }

    @GetMapping
    public List<Flavor> getAllFlavors(){
        return flavorService.listAllFlavors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flavor> getFlavorById(@PathVariable Long id){
        Optional<Flavor> flavor = flavorService.findById(id);
        if(flavor.isPresent()){
            return ResponseEntity.ok(flavor.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Flavor> createFlavor(@RequestBody Flavor flavor){
        flavorService.saveFlavor(flavor);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flavor> updateFlavor(@PathVariable Long id, @RequestBody Flavor flavor){
        Optional<Flavor> flavorDb = flavorService.findById(id);
        if(flavorDb.isPresent()){
            flavorDb.get().setName(flavor.getName());
            flavorDb.get().setIngredients(flavor.getIngredients());
            flavorService.saveFlavor(flavorDb.get());
        }
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Flavor> deleteFlavor(@PathVariable Long id){
        Optional<Flavor> flavorDb = flavorService.findById(id);
        if(flavorDb.isPresent()){
            flavorService.deleteFlavor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
