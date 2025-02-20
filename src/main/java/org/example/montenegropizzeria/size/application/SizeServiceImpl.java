package org.example.montenegropizzeria.size.application;

import org.example.montenegropizzeria.size.domain.Size;
import org.example.montenegropizzeria.size.domain.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Autowired
    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<Size> findAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<Size> findSizeById(Long id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Size createSize(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void deleteSizeById(Long id) {
        sizeRepository.deleteById(id);
    }
}
