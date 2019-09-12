package com.github.szsalyi.pulzator.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productMeasure.name) = LOWER(:productMeasureName)")
    List<Product> findByProductMeasureName(String productMeasureName);

    List<Product> findByNameContaining(String name);
}
