package ma.fst.security.config;

import ma.fst.security.entities.Role;
import ma.fst.security.entities.User;
import ma.fst.security.repositories.RoleRepository;
import ma.fst.security.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("--- Début de l'initialisation des données ---");


        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
            System.out.println("Rôle USER créé.");
        }

        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Rôle ADMIN créé.");
        }


        User admin = userRepository.findByUsername("admin");

        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");

            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setActive(true);
            admin.setRoles(List.of(adminRole));

            userRepository.save(admin);
            System.out.println("Utilisateur admin créé (mdp: 1234).");
        } else {
            System.out.println("L'utilisateur admin existe déjà.");
        }

        System.out.println("--- Fin de l'initialisation ---");
    }
}