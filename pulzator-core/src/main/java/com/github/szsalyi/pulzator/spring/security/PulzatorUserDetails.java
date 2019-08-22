package com.github.szsalyi.pulzator.spring.security;


import com.github.szsalyi.pulzator.users.UserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class PulzatorUserDetails extends User {

    private UserVO user;

    public PulzatorUserDetails(final UserVO user) {
        super(user.getUsername(), user.getPassword(), getAuthority(user));
        this.user = user;
    }

    public static List<GrantedAuthority> getAuthority(final UserVO user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }

}

