package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.dto.User;
import org.example.dto.UserLogin;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.JWT.JwtService.JWTService;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(User user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity savedUser = userRepository.save(userEntity);

        String jwtToken = jwtService.generateToken(savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(UserLogin userLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );
        var user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public List<User> getAll() {

        List<UserEntity> all = userRepository.findAll();

        List<User> users = new ArrayList<>();

        all.forEach(userEntity -> {
            users.add(mapper.map(userEntity,User.class));
        });
        return users;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Integer id) {

    }
}
