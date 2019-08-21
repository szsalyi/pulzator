package com.github.szsalyi.pulzator.users;

import com.github.szsalyi.pulzator.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private boolean enabled;
    private Set<Role> role;
}
