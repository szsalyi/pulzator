package com.github.szsalyi.pulzator.products;

import com.github.szsalyi.pulzator.categories.CategoryService;
import com.github.szsalyi.pulzator.categories.CategoryVO;
import com.github.szsalyi.pulzator.common.mapping.CycleAvoidingContextMapping;
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
        Product product = ProductMapper.INSTANCE.toEntity(productVO, new CycleAvoidingContextMapping());
        product.setProductMeasure(productMeasureRepository
                .findById(productVO.getProductMeasure().getId()).get());

        ProductVO savedProduct = ProductMapper.INSTANCE.toVO(productRepository.save(product), new CycleAvoidingContextMapping());

        CategoryVO categoryVO = categoryService.getById(productVO.getCategory().getId());
        savedProduct.setCategory(categoryVO);
        return savedProduct;
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
        productRepository.delete(ProductMapper.INSTANCE.toEntity(productVO, new CycleAvoidingContextMapping()));
    }

    @Override
    public Optional<ProductVO> loadById(final Long id) {
        return Optional.of(ProductMapper.INSTANCE.toVO(productRepository.findById(id).get(), new CycleAvoidingContextMapping()));
    }

    @Override
    public Optional<ProductVO> loadByName(final String name) throws Exception {
        return Optional.of(ProductMapper.INSTANCE.toVO(productRepository.findByName(name)
                .orElseThrow(Exception::new), new CycleAvoidingContextMapping()));
    }

    @Override
    public List<ProductVO> loadByCategory(final String categoryName) {
        List<Product> products = productRepository.findByProductMeasureName(categoryName);

        return products.stream()
                .map(p -> ProductMapper.INSTANCE.toVO(p, new CycleAvoidingContextMapping()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductVO> loadAll() {
        return productMapper.productsToVo(productRepository.findAll());
    }

}
