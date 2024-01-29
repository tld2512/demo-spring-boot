package com.example.demo.service.product;

import com.example.demo.entity.dto.product.DTOConverter;
import com.example.demo.entity.dto.product.ProductDTO;
import com.example.demo.entity.model.Category;
import com.example.demo.entity.model.Product;
import com.example.demo.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final IProductRepository IProductRepository;
    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    public ProductService(IProductRepository IProductRepository) {
        this.IProductRepository = IProductRepository;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        this.IProductRepository.save(this.dtoConverter.convertToProduct(productDTO));
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        this.IProductRepository.save(this.dtoConverter.convertToProduct(productDTO));
    }

    @Override
    public List<Product> findAll() {
        return this.IProductRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return this.IProductRepository.findByProductNameContains(name);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return this.IProductRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findAllAndSortingAsc() {
        return this.IProductRepository.findAllAndSortingAsc();
    }

    @Override
    public void delete(String pid) {
        this.IProductRepository.deleteById(pid);
    }

    @Override
    public boolean isProductIdExisted(int pid) {
        Optional<Product> product = findProductById(pid);
        return product.isPresent();
    }

    @Override
    public Optional<Product> findProductById(int pid) {
        return this.IProductRepository.findById(String.valueOf(pid));
    }


}
