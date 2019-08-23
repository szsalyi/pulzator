package com.github.szsalyi.pulzator.products;

import com.github.szsalyi.pulzator.common.mapping.CycleAvoidingContextMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "category.products", ignore = true)
    })
    Product toEntity(ProductVO productVO, @Context CycleAvoidingContextMapping context);

    ProductVO toVO(Product product, @Context CycleAvoidingContextMapping context);

    List<ProductVO> productsToVo(List<Product> products);

    List<Product> vosToProduct(List<ProductVO> products);
}

