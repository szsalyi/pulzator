package com.github.szsalyi.pulzator.products;

import com.github.szsalyi.pulzator.categories.CategoryService;
import com.github.szsalyi.pulzator.categories.CategoryVO;
import com.github.szsalyi.pulzator.productmeasures.ProductMeasure;
import com.github.szsalyi.pulzator.productmeasures.ProductMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMeasureRepository productMeasureRepository;

    @Override
    public ProductVO save(final ProductVO productVO) {
        CategoryVO categoryVO = categoryService.getById(productVO.getCategory().getId());
        productVO.setCategory(categoryVO);
        ProductMeasure productMeasure = productMeasureRepository.findById(productVO.getProductMeasure().getId()).get();
        productVO.setProductMeasure(productMeasure);
        Product product = productMapper.toEntity(productVO);

        Product save = productRepository.save(product);
        return productMapper.toVO(save);
    }

    @Override
    public void delete(final Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Override
    public void delete(final String name) {
        Product product = productRepository.findByName(name).get();
        this.delete(product.getId());
    }

    @Override
    public void delete(final ProductVO productVO) {
        productRepository.delete(productMapper.toEntity(productVO));
    }

    @Override
    public Optional<ProductVO> loadById(final Long id) {
        return Optional.of(productMapper.toVO(productRepository.findById(id).get()));
    }

    @Override
    public Optional<ProductVO> loadByName(final String name) throws Exception {
        return Optional.of(productMapper.toVO(productRepository.findByName(name)
                .orElseThrow(Exception::new)));
    }

    @Override
    public Optional<List<ProductVO>> loadByNameContaining(final String name) throws Exception {
        List<Product> result = productRepository.findByNameContaining(name);
        return Optional.of(productMapper.productsToVo(result));
    }

    @Override
    public List<ProductVO> loadByCategory(final String categoryName) {
        List<Product> products = productRepository.findByProductMeasureName(categoryName);

        return products.stream()
                .map(p -> productMapper.toVO(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductVO> loadAll() {
        return productMapper.productsToVo(productRepository.findAll());
    }

}
