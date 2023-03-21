package ru.saffonov.springboot.dao;

import org.springframework.stereotype.Repository;
import jakarta.persistence.*;
import ru.saffonov.springboot.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional get(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void saveUser(User user) {entityManager.persist(user); }
//    public void saveUser(User user) {executeInsideTransaction(entityManager -> entityManager.persist(user));}

    @Override
    public void deleteUserById(Long id) {entityManager.remove(entityManager.find(User.class, id)); }
//    public void deleteUser(User user) {executeInsideTransaction(entityManager -> entityManager.remove(user));}

    @Override
    public User getUserById(Long id) {return entityManager.find(User.class, id);}
/*    public void getUserById(User user, String[] params) {
        user.setId(Objects.requireNonNull(params[0], "Name cannot be null"));
        user.setEmail(Objects.requireNonNull(params[1], "Email cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(user));
    }
*/
    @Override
    public List<User> getAllUser() { return entityManager.createQuery("FROM User").getResultList(); }
/*    public List getAllUser() {
        Query query = entityManager.createQuery("SELECT e FROM User e");
        return query.getResultList();
    }

    private void executeInsideTransaction(Consumer action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
*/

    @Override
    public void userEditor(User user, Long id) {
        User existingUser = entityManager.find(User.class, id);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            entityManager.merge(existingUser);
        }
    }
}
