package com.github.szsalyi.pulzator.users;

import com.github.szsalyi.pulzator.role.Role;
import com.github.szsalyi.pulzator.role.RoleName;
import com.github.szsalyi.pulzator.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO save(final UserVO userVO) throws Exception {
        User userEntity = userMapper.toEntity(userVO);

        Role role = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                .orElseThrow(() -> new Exception("Role doesn't exists!"));

        userEntity.setRole(Collections.singleton(role));

        return userMapper.toVO(userRepository.save(userEntity));
    }
    @Override
    public UserVO findByEmail(final String email) {
        return userMapper.toVO(userRepository.findByEmail(email));
    }
}

