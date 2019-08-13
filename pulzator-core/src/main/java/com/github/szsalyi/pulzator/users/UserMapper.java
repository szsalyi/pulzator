package com.github.szsalyi.pulzator.users;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({

    })
    User toEntity(UserVO userVO);

    @InheritInverseConfiguration
    UserVO toVO(User user);
}
