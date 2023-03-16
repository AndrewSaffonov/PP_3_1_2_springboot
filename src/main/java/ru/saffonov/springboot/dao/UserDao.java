package ru.saffonov.springboot.dao;

import ru.saffonov.springboot.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    void deleteUserById(Long id);

    User getUserById(Long id);

    List<User> getAllUser();

    void userEditor(User user, Long id);
}
