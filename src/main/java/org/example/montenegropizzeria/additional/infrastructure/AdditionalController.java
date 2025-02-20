package org.example.montenegropizzeria.additional.infrastructure;

import org.example.montenegropizzeria.additional.application.AdditionalServiceImpl;
import org.example.montenegropizzeria.additional.domain.AdditionalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class AdditionalController {

    private final AdditionalServiceImpl additionalServiceImpl;

    @Autowired
    public AdditionalController(AdditionalServiceImpl additionalServiceImpl) {
        this.additionalServiceImpl = additionalServiceImpl;
    }

    @PostMapping("/addAdditional")
    @PreAuthorize("hasRole('ADMIN')")
    public AdditionalDTO addAdditional(@RequestBody AdditionalDTO additionalDTO) {
        return additionalServiceImpl.saveAdditional(additionalDTO);
    }

    @PostMapping("/listAllAdditional")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdditionalDTO> getAllAdditional() {
        return additionalServiceImpl.findAllAdditional();
    }
    @GetMapping("/getAdditional/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdditionalDTO getAdditionalById(@PathVariable Long id) {
        return additionalServiceImpl.findAdditionalById(id)
                .orElseThrow(() -> new RuntimeException("Additional not found with id: " + id));
    }

    @DeleteMapping("/deleteAdditional/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAdditionalById(@PathVariable Long id) {
        additionalServiceImpl.deleteAdditionalById(id);
    }

    @PutMapping("/updateAdditional/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdditionalDTO updateAdditionalById(@PathVariable Long id, @RequestBody AdditionalDTO additionalDTO) {
        if (!additionalServiceImpl.findAdditionalById(id).isPresent()) {
            throw new RuntimeException("Additional not found with id: " + id);
        }
        additionalDTO.setId(id);
        return additionalServiceImpl.saveAdditional(additionalDTO);
    }

}
