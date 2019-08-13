package com.github.szsalyi.pulzator.users;

public interface UserService {

    UserVO save(UserVO userVO);
    UserVO findByEmail(String email);
}
