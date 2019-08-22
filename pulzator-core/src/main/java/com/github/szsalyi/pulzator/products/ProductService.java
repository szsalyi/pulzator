package com.github.szsalyi.pulzator.products;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductVO save(ProductVO productVO);
    void delete(Long id) throws Exception;
    void delete(String name) throws Exception;
    Optional<ProductVO> loadById(Long id) throws Exception;
    Optional<ProductVO> loadByName(String name) throws Exception;
    List<ProductVO> loadByCategory(String categoryName);
    List<ProductVO> loadAll();
}
