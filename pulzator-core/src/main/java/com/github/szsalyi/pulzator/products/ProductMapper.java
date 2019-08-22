package com.github.szsalyi.pulzator.products;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductVO productVO);

    ProductVO toVO(Product product);

    List<ProductVO> productsToVo(List<Product> products);

    List<Product> vosToProduct(List<ProductVO> products);
}

