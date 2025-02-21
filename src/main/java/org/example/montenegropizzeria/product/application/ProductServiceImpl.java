package org.example.montenegropizzeria.product.application;

import org.example.montenegropizzeria.flavor.domain.Flavor;
import org.example.montenegropizzeria.flavor.domain.FlavorRepository;
import org.example.montenegropizzeria.product.domain.Product;
import org.example.montenegropizzeria.product.domain.ProductDTO;
import org.example.montenegropizzeria.product.domain.ProductRepository;
import org.example.montenegropizzeria.size.domain.Size;
import org.example.montenegropizzeria.size.domain.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FlavorRepository flavorRepository;
    private final SizeRepository sizeRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FlavorRepository flavorRepository, SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.flavorRepository = flavorRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setImage(productDTO.getImageUrl());

        List<Flavor> flavors = flavorRepository.findAllById(productDTO.getFlavorsIds());
        List<Size> sizes = sizeRepository.findAllById(productDTO.getSizesIds());

        product.setFlavors(flavors);
        product.setSizes(sizes);

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> updateProduct(Long id, ProductDTO productDTO) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setName(productDTO.getName());
            product.get().setImage(productDTO.getImageUrl());

            List<Flavor> flavors = flavorRepository.findAllById(productDTO.getFlavorsIds());
            List<Size> sizes = sizeRepository.findAllById(productDTO.getSizesIds());

            product.get().setFlavors(flavors);
            product.get().setSizes(sizes);

            return Optional.of(productRepository.save(product.get()));
        }

        return Optional.empty();
    }
}
