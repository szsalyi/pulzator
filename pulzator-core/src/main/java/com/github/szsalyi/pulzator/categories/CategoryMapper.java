package com.github.szsalyi.pulzator.categories;

import com.github.szsalyi.pulzator.products.Product;
import com.github.szsalyi.pulzator.products.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryVO toVO(Category category);

    Category toEntity(CategoryVO categoryVO);

    List<CategoryVO> categoryToVOsWithProducts(List<Category> category);

    List<Category> categoryVOsToCategoriesWithProducts(List<Category> category);

    @Mappings({
            @Mapping(target = "category", ignore = true)
    })
    ProductVO productToVOWithoutCategory(Product product);
}
