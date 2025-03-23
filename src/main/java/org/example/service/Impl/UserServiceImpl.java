package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.User;
import org.example.dto.UserLogin;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.JwtService.JWTService;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void addUser(User user) {
        //user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(mapper.map(user, UserEntity.class));
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

    @Override
    public String verify(UserLogin userLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getFirstName(),userLogin.getPassword())
        );

        if (authentication.isAuthenticated()){
            return jwtService.genarateToken(userLogin.getFirstName());
        }
        return "fail";
    }
}
