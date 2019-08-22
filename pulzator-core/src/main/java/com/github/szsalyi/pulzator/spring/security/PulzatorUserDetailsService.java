package com.github.szsalyi.pulzator.spring.security;

import com.github.szsalyi.pulzator.users.UserService;
import com.github.szsalyi.pulzator.users.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PulzatorUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String usernameOrEmail) throws UsernameNotFoundException {
        UserVO userVo = userService.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with details: " + usernameOrEmail));

        return new PulzatorUserDetails(userVo);
    }

    @Transactional
    public UserDetails loadUserById(final Long id) {
        UserVO user = userService.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new PulzatorUserDetails(user);

    }

}
