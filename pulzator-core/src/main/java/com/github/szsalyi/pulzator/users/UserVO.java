package com.github.szsalyi.pulzator.users;

import com.github.szsalyi.pulzator.roles.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserVO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean enabled;
    private Set<Role> roles;
}
