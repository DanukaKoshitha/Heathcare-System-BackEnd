package org.example.service;

import org.example.JWT.AuthenticationResponse;
import org.example.dto.User;
import org.example.dto.UserLogin;
import java.util.List;

public interface UserService {
    AuthenticationResponse register(User user);
    AuthenticationResponse login(UserLogin userLogin);
    List<User> getAll();
    User findById(Integer id);
    void updateUser(User user);
    void deleteUser(Integer id);

}
