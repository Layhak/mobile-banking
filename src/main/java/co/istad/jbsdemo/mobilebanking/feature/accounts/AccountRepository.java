package co.istad.jbsdemo.mobilebanking.feature.accounts;

import co.istad.jbsdemo.mobilebanking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
