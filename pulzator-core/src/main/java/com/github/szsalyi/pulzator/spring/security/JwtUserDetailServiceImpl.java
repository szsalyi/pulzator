package com.github.szsalyi.pulzator.spring.security;

import com.github.szsalyi.pulzator.users.User;
import com.github.szsalyi.pulzator.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "jwtUserDetailService")
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with '%s' ", s));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
