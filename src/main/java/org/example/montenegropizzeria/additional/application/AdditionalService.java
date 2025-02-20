package org.example.montenegropizzeria.additional.application;

import org.example.montenegropizzeria.additional.domain.Additional;
import org.example.montenegropizzeria.additional.domain.AdditionalDTO;

import java.util.List;
import java.util.Optional;

public interface AdditionalService {
    List<AdditionalDTO> findAllAdditional();
    Optional<AdditionalDTO> findAdditionalById(Long id);
    void deleteAdditionalById(Long id);
    AdditionalDTO saveAdditional(AdditionalDTO additionalDTO);
}
