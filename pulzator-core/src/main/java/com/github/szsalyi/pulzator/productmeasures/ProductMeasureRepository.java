package com.github.szsalyi.pulzator.productmeasures;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMeasureRepository extends JpaRepository<ProductMeasure, Long> {

    ProductMeasure findByName(String name);
}
