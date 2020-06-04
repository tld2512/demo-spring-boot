package com.example.demo.service.product;

import com.example.demo.entity.dto.product.ProductDTO;
import com.example.demo.entity.model.Category;
import com.example.demo.entity.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    void addProduct(ProductDTO product);

    void updateProduct(ProductDTO product);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findAllAndSortingAsc();

    void delete(String pid);

    boolean isProductIdExisted(String pid);

    Optional<Product> findProductById(String pid);
}
