package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.User;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public void addUser(User user) {
        userRepository.save(mapper.map(user, UserEntity.class));
    }

    @Override
    public List<User> getAll() {

//        List<UserEntity> all = userRepository.findAll();
//
//        List<User> users = new ArrayList<>();
//
//        all.forEach(userEntity -> {
//            users.add(mapper.map(userEntity,User.class));
//        });
//        return users;

        return List.of();
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
