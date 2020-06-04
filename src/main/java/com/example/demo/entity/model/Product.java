package com.example.demo.entity.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @NotNull(message = "Please enter product ID")
    private String id;

    @Column(name = "product_name")
    @NotNull(message = "Please enter product name")
    @Size(min = 3, message = "Product name must longer than 3 character")
    private String product_name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Please enter product price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String id, String product_name, String description, Double price, Category category) {
        this.id = id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

