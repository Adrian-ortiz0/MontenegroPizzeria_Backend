package org.example.montenegropizzeria.additional.application;

import org.example.montenegropizzeria.additional.domain.Additional;
import org.example.montenegropizzeria.additional.domain.AdditionalDTO;
import org.example.montenegropizzeria.additional.domain.AdditionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceImpl implements AdditionalService {

    private final AdditionalRepository additionalRepository;

    public AdditionalServiceImpl(AdditionalRepository additionalRepository) {
        this.additionalRepository = additionalRepository;
    }

    @Override
    public List<AdditionalDTO> findAllAdditional() {
        return additionalRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdditionalDTO> findAdditionalById(Long id) {
        return additionalRepository.findById(id).map(this::toDTO);
    }

    @Override
    public void deleteAdditionalById(Long id) {
        additionalRepository.deleteById(id);
    }

    @Override
    public AdditionalDTO saveAdditional(AdditionalDTO additionalDTO) {
        Additional additional = toEntity(additionalDTO);
        Additional savedAdditional = additionalRepository.save(additional);
        return toDTO(savedAdditional);
    }

    public AdditionalDTO updateAdditional(AdditionalDTO additionalDTO) {
        Additional additional = toEntity(additionalDTO);
        Additional savedAdditional = additionalRepository.save(additional);
        return toDTO(savedAdditional);
    }


    private AdditionalDTO toDTO(Additional additional) {
        return new AdditionalDTO(additional.getId(), additional.getName(), additional.getPrice());
    }

    private Additional toEntity(AdditionalDTO additionalDTO) {
        Additional additional = new Additional();
        additional.setName(additionalDTO.getName());
        additional.setPrice(additionalDTO.getPrice());
        additional.setId(additionalDTO.getId());
        return additional;
    }
}
