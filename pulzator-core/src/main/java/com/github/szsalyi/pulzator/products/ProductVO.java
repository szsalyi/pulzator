package com.github.szsalyi.pulzator.products;

import com.github.szsalyi.pulzator.categories.CategoryVO;
import com.github.szsalyi.pulzator.productmeasures.ProductMeasure;
import lombok.Data;

@Data
public class ProductVO {

    private Long id;
    private String name;
    private int quantity;
    private ProductMeasure productMeasure;
    private int price;
    private boolean enabled;
    private CategoryVO category;
}
