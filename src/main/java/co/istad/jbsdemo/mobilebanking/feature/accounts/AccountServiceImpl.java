package co.istad.jbsdemo.mobilebanking.feature.accounts;

import co.istad.jbsdemo.mobilebanking.domain.Account;
import co.istad.jbsdemo.mobilebanking.domain.AccountType;
import co.istad.jbsdemo.mobilebanking.domain.User;
import co.istad.jbsdemo.mobilebanking.domain.UserAccount;
import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountRequest;
import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountResponse;
import co.istad.jbsdemo.mobilebanking.feature.acounttype.AccountTypeRepository;
import co.istad.jbsdemo.mobilebanking.feature.user.UserRepository;
import co.istad.jbsdemo.mobilebanking.feature.userAccount.UserAccountRepository;
import co.istad.jbsdemo.mobilebanking.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public List<AccountResponse> getAllAccounts() {
        return userAccountRepository.findAll().stream().map(accountMapper::mapUserAccountToAccountResponse).toList();
    }


    @Override
    public AccountResponse createAccount(AccountRequest request) {
        // check account type
        AccountType accountType = accountTypeRepository
                .findByName(request.accountType())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Account Type : " + request.accountType() + " is not valid! "));
        User owner = userRepository.findById(request.userId())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "User ID = " + request.userId() + " is not a valid user"
                        )
                );
        if (userAccountRepository.countAccountsByUserId(request.userId()) >= 5) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User ID = " + request.userId() + " already has 5 accounts"
            );
        }
        Account account = accountMapper.mapRequestToEntity(request);
        account.setAccountType(accountType);
        // account info from the request
        UserAccount userAccount = new UserAccount()
                .setAccount(account)
                .setUser(owner)
                .setDisabled(false);
        userAccountRepository.save(userAccount);
        return accountMapper.mapUserAccountToAccountResponse(userAccount);
    }


    @Override
    public AccountResponse findByAccountId(String id) {
        UserAccount userAccount = userAccountRepository.findByAccount_id(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return accountMapper.mapUserAccountToAccountResponse(userAccount);
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        UserAccount account = userAccountRepository.findByAccount_AccountNumber(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with Account Number=" + accountNumber + "doesn't exist."));
        return accountMapper.mapUserAccountToAccountResponse(account);
    }

    @Override
    public List<AccountResponse> findAccountByUserId(String userId) {
        List<UserAccount> userAccount = userAccountRepository.findByUser_Id(userId);
        return userAccount.stream().map(accountMapper::mapUserAccountToAccountResponse).toList();
    }

    @Override
    public AccountResponse disableAccountById(String id) {
        int affectedRow = userAccountRepository.updateDisableStatusById(id, true);
        if (affectedRow > 0) {
            return accountMapper.mapUserAccountToAccountResponse(userAccountRepository.findById(id).orElse(null));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Account Id: " + id + "dosen't exist.");
    }

    @Override
    public AccountResponse enableAccountById(String id) {
        int affectedRow = userAccountRepository.updateDisableStatusById(id, false);
        if (affectedRow > 0) {
            return accountMapper.mapUserAccountToAccountResponse(userAccountRepository.findById(id).orElse(null));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Account Id: " + id + "dosen't exist.");
    }
}
