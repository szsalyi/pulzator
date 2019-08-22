package com.github.szsalyi.pulzator.products;

import lombok.Data;

@Data
public class ProductVO {

    private Long id;
    private String name;
    private int quantity;
    private ProductMeasure productMeasure;
    private int price;
    private boolean enabled;
}
