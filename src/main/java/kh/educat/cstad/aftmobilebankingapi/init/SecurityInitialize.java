package kh.educat.cstad.aftmobilebankingapi.init;


import jakarta.annotation.PostConstruct;
import kh.educat.cstad.aftmobilebankingapi.domain.Role;
import kh.educat.cstad.aftmobilebankingapi.domain.User;
import kh.educat.cstad.aftmobilebankingapi.repository.RoleRepository;
import kh.educat.cstad.aftmobilebankingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityInitialize {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        if (roleRepository.count() == 0) {
            // Save roles
            Role roleUser = new Role();
            roleUser.setName("USER");

            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");

            Role roleStaff = new Role();
            roleStaff.setName("STAFF");

            Role roleCustomer = new Role();
            roleCustomer.setName("CUSTOMER");

            roleRepository.saveAll(List.of(roleUser, roleAdmin, roleStaff, roleCustomer));
            roleRepository.flush();


            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123$"));
            admin.setIsEnabled(true);
            admin.setRoles(Set.of(roleUser, roleAdmin));

            User staff = new User();
            staff.setUsername("staff");
            staff.setPassword(passwordEncoder.encode("admin123$"));
            staff.setIsEnabled(true);
            staff.setRoles(Set.of(roleUser, roleStaff));

            User customer = new User();
            customer.setUsername("customer");
            customer.setPassword(passwordEncoder.encode("admin123$"));
            customer.setIsEnabled(true);
            customer.setRoles(Set.of(roleUser, roleCustomer));

            userRepository.saveAll(List.of(admin, staff, customer));
        }


    }

}