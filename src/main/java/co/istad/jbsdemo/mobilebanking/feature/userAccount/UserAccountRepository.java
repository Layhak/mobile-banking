package co.istad.jbsdemo.mobilebanking.feature.userAccount;

import co.istad.jbsdemo.mobilebanking.domain.UserAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    //? find useraccount by it's fields account -> account id
    Optional<UserAccount> findByAccount_id(String id);

    Optional<UserAccount> findByAccount_AccountNumber(String accountNumber);

    List<UserAccount> findByUser_Id(String userId);

    //    @Modifying
    @Modifying
    @Transactional
    @Query("UPDATE user_account_tbl ua set ua.isDisabled = :status where ua.id = :id")
    int updateDisableStatusById(String id, boolean status);

    @Query("SELECT COUNT(ua) FROM  user_account_tbl ua WHERE ua.user.id = ?1")
    int countAccountsByUserId(@Param("userId") String userId);

}
