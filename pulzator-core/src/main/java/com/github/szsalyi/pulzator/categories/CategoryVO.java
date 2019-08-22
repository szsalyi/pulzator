package com.github.szsalyi.pulzator.categories;

import com.github.szsalyi.pulzator.products.ProductVO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {

    private Long id;

    private String name;

    private List<ProductVO> products;

}
