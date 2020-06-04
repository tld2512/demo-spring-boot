package com.example.demo.repository.product;

import com.example.demo.entity.model.Category;
import com.example.demo.entity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p where p.product_name like %:name%")
    List<Product> findByProductNameContains(@Param("name") String name);

    List<Product> findAllByCategory(Category category);

    @Query("FROM Product order by price asc")
    List<Product> findAllAndSortingAsc();
}
