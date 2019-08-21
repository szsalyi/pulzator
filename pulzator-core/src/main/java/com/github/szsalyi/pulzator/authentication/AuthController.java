package com.github.szsalyi.pulzator.authentication;

import com.github.szsalyi.pulzator.exceptions.AppException;
import com.github.szsalyi.pulzator.requests.LoginRequest;
import com.github.szsalyi.pulzator.requests.SignUpRequest;
import com.github.szsalyi.pulzator.responses.ApiResponse;
import com.github.szsalyi.pulzator.responses.JwtAuthenticationResponse;
import com.github.szsalyi.pulzator.role.Role;
import com.github.szsalyi.pulzator.role.RoleName;
import com.github.szsalyi.pulzator.role.RoleRepository;
import com.github.szsalyi.pulzator.spring.security.JWTTokenProvider;
import com.github.szsalyi.pulzator.users.UserMapper;
import com.github.szsalyi.pulzator.users.UserRepository;
import com.github.szsalyi.pulzator.users.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody final LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody final SignUpRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findByEmail(signUpRequest.getEmail()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserVO user = new UserVO(signUpRequest.getUsername(), signUpRequest.getName().split(" ")[0],
                signUpRequest.getName().split(" ")[1], signUpRequest.getEmail(), signUpRequest.getPassword());

        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRole(Collections.singleton(userRole));

        UserVO result = userMapper.toVO(userRepository.save(userMapper.toEntity(user)));

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getFirstName() + " " + result.getLastName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
