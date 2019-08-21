package com.github.szsalyi.pulzator.spring.security;

import com.github.szsalyi.pulzator.users.UserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class PulzatorUserDetails extends User {

    private UserVO userVO;

    public PulzatorUserDetails(final UserVO userVO) {
        super(userVO.getUsername(), userVO.getPassword(), getAuthorities(userVO));
        this.userVO = userVO;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(final UserVO userVO) {
        return userVO.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return this.userVO.getId();
    }
}
