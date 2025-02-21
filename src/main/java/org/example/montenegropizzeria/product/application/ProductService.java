package org.example.montenegropizzeria.product.application;

import org.example.montenegropizzeria.product.domain.Product;
import org.example.montenegropizzeria.product.domain.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(ProductDTO productDTO);
    List<Product> findAllProducts();
    Optional<Product> findProductById(Long id);
    void deleteProduct(Long id);
    Optional<Product> updateProduct(Long id, ProductDTO productDTO);
}
