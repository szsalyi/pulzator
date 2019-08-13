package com.github.szsalyi.pulzator.users;

import com.github.szsalyi.pulzator.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO save(final UserVO userVO) {
        User userEntity = userMapper.toEntity(userVO);

        return userMapper.toVO(userRepository.save(userEntity));
    }
    @Override
    public UserVO findByEmail(final String email) {
        return userMapper.toVO(userRepository.findByEmail(email));
    }
}

