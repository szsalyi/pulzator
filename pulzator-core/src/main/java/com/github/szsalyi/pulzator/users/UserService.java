package com.github.szsalyi.pulzator.users;

import java.util.Optional;

public interface UserService {

    Optional<UserVO> findById(Long id);
    UserVO save(UserVO userVO) throws Exception;
    UserVO findByEmail(String email);
    Optional<UserVO> findByUsernameOrEmail(String usernameOrEmail);
}
