package org.example.montenegropizzeria.size.application;

import org.example.montenegropizzeria.size.domain.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {
    List<Size> findAllSizes();
    Optional<Size> findSizeById(Long id);
    Size createSize(Size size);
    void deleteSizeById(Long id);
}
