package co.istad.jbsdemo.mobilebanking.feature.roles;

import co.istad.jbsdemo.mobilebanking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(String role);
}
