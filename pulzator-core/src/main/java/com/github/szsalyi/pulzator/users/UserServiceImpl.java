package com.github.szsalyi.pulzator.users;

import com.github.szsalyi.pulzator.roles.Role;
import com.github.szsalyi.pulzator.roles.RoleName;
import com.github.szsalyi.pulzator.roles.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<UserVO> findById(final Long id) {
        return Optional.of(userMapper.toVO(userRepository.findById(id).get()));
    }

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

    @Override
    public Optional<UserVO> findByUsernameOrEmail(final String usernameOrEmail) {
        return Optional.of(userMapper.toVO(userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)));
    }
}

