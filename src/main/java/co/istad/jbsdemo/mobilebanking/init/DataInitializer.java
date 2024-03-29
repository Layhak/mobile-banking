package co.istad.jbsdemo.mobilebanking.init;

import co.istad.jbsdemo.mobilebanking.domain.Role;
import co.istad.jbsdemo.mobilebanking.feature.roles.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

//populate data (role with some data)
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;

    @PostConstruct
    void roleInit() {
        List<String> roles = List.of("Admin", "Stuff", "User");
        if (roleRepository.findAll().isEmpty()) {
            for (String role : roles) {
                Role roleObj = new Role();
                roleObj.setName(role);
                roleRepository.save(roleObj);
            }
        }
    }

}
