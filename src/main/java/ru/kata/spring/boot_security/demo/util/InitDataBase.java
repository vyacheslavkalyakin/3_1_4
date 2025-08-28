package ru.kata.spring.boot_security.demo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Component
public class InitDataBase implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDataBase(UserRepository userRepository,
                        RoleRepository roleRepository,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (roleRepository.findByName("ROLE_ADMIN").isPresent()
                && roleRepository.findByName("ROLE_USER").isPresent()) {
            return;
        }

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User admin = new User("Admin", "Adminov", "admin@example.com",
                    passwordEncoder.encode("admin"), 30);
            admin.getRoles().add(roleAdmin);
            admin.getRoles().add(roleUser);
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("user@example.com").isEmpty()) {
            User user = new User("User", "Userov", "user@example.com",
                    passwordEncoder.encode("user"), 25);
            user.getRoles().add(roleUser);
            userRepository.save(user);
        }
    }
}