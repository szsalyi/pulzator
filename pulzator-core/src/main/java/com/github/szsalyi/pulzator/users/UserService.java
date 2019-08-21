package com.github.szsalyi.pulzator.users;

import java.util.Optional;

public interface UserService {

    UserVO save(UserVO userVO) throws Exception;
    UserVO findByEmail(String email);
    UserVO findByUsername(String username);
    Optional<UserVO> findByUsernameOrEmail(String username, String email);
    Optional<UserVO> findById(Long id);
}
