package org.example.montenegropizzeria.product.domain;

import java.util.List;

public class ProductDTO {

    private String name;
    private List<Long> flavorsIds;
    private List<Long> sizesIds;
    private String imageUrl;

    public ProductDTO() {
    }

    public ProductDTO(String name, List<Long> flavorsIds, List<Long> sizesIds, String imageUrl) {
        this.name = name;
        this.flavorsIds = flavorsIds;
        this.sizesIds = sizesIds;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getFlavorsIds() {
        return flavorsIds;
    }

    public void setFlavorsIds(List<Long> flavorsIds) {
        this.flavorsIds = flavorsIds;
    }

    public List<Long> getSizesIds() {
        return sizesIds;
    }

    public void setSizesIds(List<Long> sizesIds) {
        this.sizesIds = sizesIds;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
