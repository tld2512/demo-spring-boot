package com.example.demo.entity.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String product_name;
    private String description;
    private Double price;
    private int categoryID;
}
