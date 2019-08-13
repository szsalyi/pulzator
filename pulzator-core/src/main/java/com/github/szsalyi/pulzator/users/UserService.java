package com.github.szsalyi.pulzator.users;

public interface UserService {

    UserVO save(UserVO userVO) throws Exception;
    UserVO findByEmail(String email);
}
