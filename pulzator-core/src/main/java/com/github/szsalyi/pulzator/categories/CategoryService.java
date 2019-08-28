package com.github.szsalyi.pulzator.categories;

import java.util.List;

public interface CategoryService {

    CategoryVO getById(Long id);
    CategoryVO save(CategoryVO categoryVO);
    void delete(Long id);
    List<CategoryVO> getAll();
}
