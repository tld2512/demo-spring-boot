package com.example.demo.entity.model;

import com.example.demo.entity.account.User;
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
    private int id;

    @Column(name = "productName")
    @NotNull(message = "Please enter product name")
    @Size(min = 3, message = "Product name must longer than 3 character")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Please enter product price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Product(int id, String product_name, String description, Double price, Category category) {
        this.id = id;
        this.productName = product_name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}

