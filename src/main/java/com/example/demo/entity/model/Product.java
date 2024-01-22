package com.example.demo.entity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}

