package com.github.szsalyi.pulzator.categories;

import com.github.szsalyi.pulzator.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductService productService;

    @Override
    public CategoryVO getById(final Long id) {
        return categoryMapper.toVO(categoryRepository.findById(id).get());
    }

    @Override
    public CategoryVO save(final CategoryVO categoryVO) {
        Category category = categoryRepository.save(categoryMapper.toEntity(categoryVO));
        return categoryMapper.toVO(category);
    }

    @Override
    public void delete(final Long id) {
        CategoryVO categoryVO = this.getById(id);
        categoryVO.getProducts().forEach(p ->
                productService.delete(p));

        categoryRepository.delete(categoryMapper.toEntity(categoryVO));
    }

    @Override
    public List<CategoryVO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.categoryToVOsWithProducts(categories);
    }
}

