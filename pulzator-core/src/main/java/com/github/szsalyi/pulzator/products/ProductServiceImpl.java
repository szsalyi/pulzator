package com.github.szsalyi.pulzator.products;

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
    private ProductMeasureRepository  productMeasureRepository;

    @Override
    public ProductVO save(final ProductVO productVO) {
        Product product = productMapper.toEntity(productVO);
        product.setProductMeasure(productMeasureRepository.findById(productVO.getProductMeasure().getId()).get());
        return productMapper.toVO(productRepository.save(product));
    }

    @Override
    public void delete(final Long id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(Exception::new);
        productRepository.delete(product);
    }

    @Override
    public void delete(final String name) throws Exception {
        Product product = productRepository.findByName(name)
                .orElseThrow(Exception::new);
        this.delete(product.getId());
    }

    @Override
    public Optional<ProductVO> loadById(final Long id) throws Exception {
        return Optional.of(productMapper.toVO(productRepository.findById(id)
                .orElseThrow(Exception::new)));
    }

    @Override
    public Optional<ProductVO> loadByName(final String name) throws Exception {
        return Optional.of(productMapper.toVO(productRepository.findByName(name)
                .orElseThrow(Exception::new)));
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
