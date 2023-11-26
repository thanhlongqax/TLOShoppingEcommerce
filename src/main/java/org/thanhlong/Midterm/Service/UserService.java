package org.thanhlong.Midterm.Service;


import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.Models.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getAllUser();
    User updateUser(User user);
    void removeUserById(Long id);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByName(String name);
    Optional<User> getUserByUserName(String userName);
    Optional<User> getUserByEmail(String email);
    User saveUser(User user);

}
