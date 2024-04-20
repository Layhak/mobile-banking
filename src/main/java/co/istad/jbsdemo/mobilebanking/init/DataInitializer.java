package co.istad.jbsdemo.mobilebanking.init;

import co.istad.jbsdemo.mobilebanking.domain.AccountType;
import co.istad.jbsdemo.mobilebanking.domain.Role;
import co.istad.jbsdemo.mobilebanking.feature.acounttype.AccountTypeRepository;
import co.istad.jbsdemo.mobilebanking.feature.roles.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//populate data (role with some data)
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;

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

    @PostConstruct
    void accountTypesInit() {
        List<AccountType> accountTypes = new ArrayList<>() {{
            add(new AccountType().setName("Savings").setDescription("This is the type of account that you gain interest when you save your money in the bank"));

            add(new AccountType().setName("Payrolls").setDescription("This is account to get paid by company monthly"));
            add(new AccountType().setName("Card").setDescription("Allow you to create different card for persernal uses!"));
        }};
        if (accountTypeRepository.findAll().isEmpty()) {
            accountTypeRepository.saveAll(accountTypes);
        }
    }

}
