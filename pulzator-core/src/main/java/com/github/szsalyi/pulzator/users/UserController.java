package com.github.szsalyi.pulzator.users;

import com.github.szsalyi.pulzator.role.Role;
import com.github.szsalyi.pulzator.role.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping(path = "/api/users",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<UserVO> saveUser(final @RequestBody UserVO user) {
        user.setRole(new HashSet<>(Arrays.asList(new Role(RoleName.ROLE_EMPLOYEE))));
        user.setEnabled(true);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping(path = "/user", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Principal> user(final Principal user) {
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/exists", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Boolean> getUser(@RequestParam final String email) {
        if (userService.findByEmail(email) != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
