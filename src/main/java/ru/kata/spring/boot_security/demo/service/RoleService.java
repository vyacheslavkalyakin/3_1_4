package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleByName(String name);

    Role getRoleById(int id);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(int id);

}
