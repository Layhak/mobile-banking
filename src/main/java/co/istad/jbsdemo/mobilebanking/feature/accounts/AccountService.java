package co.istad.jbsdemo.mobilebanking.feature.accounts;

import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountRequest;
import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountResponse;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAllAccounts();
    AccountResponse createAccount(AccountRequest accountRequest);

    AccountResponse findByAccountId(String id);

    AccountResponse findAccountByAccountNumber(String accountNumber);

    List<AccountResponse> findAccountByUserId(String userId);
    AccountResponse disableAccountById(String id);
    AccountResponse enableAccountById(String id);
}
