package com.github.szsalyi.pulzator.products;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductVO save(ProductVO productVO);
    void delete(Long id);
    void delete(String name);
    void delete(ProductVO productVO);
    Optional<ProductVO> loadById(Long id) throws Exception;
    Optional<ProductVO> loadByName(String name) throws Exception;
    List<ProductVO> loadByCategory(String categoryName);
    List<ProductVO> loadAll();
}
