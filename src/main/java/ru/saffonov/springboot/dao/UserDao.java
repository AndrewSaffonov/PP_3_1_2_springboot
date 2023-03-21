package ru.saffonov.springboot.dao;

import ru.saffonov.springboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void saveUser(User user);

    void deleteUserById(Long id);

    User getUserById(Long id);

    List<User> getAllUser();

    void userEditor(User user, Long id);

    Optional get(long id);

/*    List getAllUser();

    void saveUser(T t);

    void updateUser(T t, String[] params);

    void deleteUser(T t);

 */
}
