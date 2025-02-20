package org.example.montenegropizzeria.size.infrastructure;

import org.example.montenegropizzeria.size.application.SizeService;
import org.example.montenegropizzeria.size.domain.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/size")
public class SizeController {

    private final SizeService sizeService;

    @Autowired
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public List<Size> getAllSizes() {
        return sizeService.findAllSizes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Size> getSizeById(@PathVariable Long id) {
        Optional<Size> sizeDb = sizeService.findSizeById(id);
        if (sizeDb.isPresent()) {
            return ResponseEntity.ok(sizeDb.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Size> createSize(@RequestBody Size size) {
        sizeService.createSize(size);
        return ResponseEntity.status(201).body(size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Size> updateSize(@PathVariable Long id, @RequestBody Size size) {
        Optional<Size> sizeDb = sizeService.findSizeById(id);
        if (sizeDb.isPresent()) {
            sizeDb.get().setSize(size.getSize());
            return ResponseEntity.status(201).body(sizeDb.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Size> deleteSize(@PathVariable Long id) {
        Optional<Size> sizeDb = sizeService.findSizeById(id);
        if (sizeDb.isPresent()) {
            sizeService.deleteSizeById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
