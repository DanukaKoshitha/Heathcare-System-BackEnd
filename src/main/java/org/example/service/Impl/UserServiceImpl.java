package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.dto.User;
import org.example.dto.LoginObject;
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
                .userId(savedUser.getId())
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginObject loginObject) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginObject.getEmail(),
                        loginObject.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginObject.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
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
        return mapper.map(userRepository.findById(id) , User.class);
    }

    @Override
    public User updateUser(User user) {

        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found with "+user.getId()));

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setAddress(user.getAddress());
        userEntity.setContact(user.getContact());
        userEntity.setRole(user.getRole());
        userEntity.setGender(user.getGender());
        userEntity.setEmail(user.getEmail());

        UserEntity updateUser = userRepository.save(userEntity);
        return mapper.map(userRepository.save(mapper.map(user , UserEntity.class)) , User.class);
    }

    @Override
    public void deleteUser(Integer id) {

    }
}
