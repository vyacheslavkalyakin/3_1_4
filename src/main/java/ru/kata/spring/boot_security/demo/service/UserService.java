package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(int id);

    User getUserById(int id);

    void editUser(int id, String password, String email, String firstName, String lastName, int age);

    List<Role> getAllRoles();

    void createUserWithRoles(User user);

    User updateUserWithRoles(int id, User updatedUser);
}
