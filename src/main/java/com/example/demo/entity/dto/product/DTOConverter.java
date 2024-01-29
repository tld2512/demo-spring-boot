package com.example.demo.entity.dto.product;

import com.example.demo.entity.model.Category;
import com.example.demo.entity.model.Product;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DTOConverter {
    @Autowired
    private ICategoryService categoryService;

    public Product convertToProduct(ProductDTO productDTO) {
        Optional<Category> category = this.categoryService.findById(productDTO.getCategoryID());
        Product product = new Product(productDTO.getId(), productDTO.getProduct_name(), productDTO.getDescription(), productDTO.getPrice(), category.get());
        return product;
    }

    public ProductDTO convertToProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getProductName(), product.getDescription(), product.getPrice(), product.getCategory().getId());
    }
}
