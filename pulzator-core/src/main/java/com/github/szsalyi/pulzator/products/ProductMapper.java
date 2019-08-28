package com.github.szsalyi.pulzator.products;

import com.github.szsalyi.pulzator.categories.Category;
import com.github.szsalyi.pulzator.categories.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "category", qualifiedByName = "categoryToVO")
    })
    Product toEntity(ProductVO productVO);

    @Mappings({
            @Mapping(target = "category", qualifiedByName = "categoryToVO")
    })
    ProductVO toVO(Product product);

    List<ProductVO> productsToVo(List<Product> products);

    List<Product> vosToProduct(List<ProductVO> products);

    @Named("categoryToVO")
    @Mappings({
            @Mapping(target = "products", expression = "java(null)")})
    CategoryVO categoryToVO(Category category);
}

