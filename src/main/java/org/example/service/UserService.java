package org.example.service;

import org.example.dto.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAll();
    User findById(Integer id);
    void updateUser(User user);
    void deleteUser(Integer id);
}
