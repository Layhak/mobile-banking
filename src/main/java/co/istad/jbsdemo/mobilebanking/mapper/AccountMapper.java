package co.istad.jbsdemo.mobilebanking.mapper;

import co.istad.jbsdemo.mobilebanking.domain.Account;
import co.istad.jbsdemo.mobilebanking.domain.User;
import co.istad.jbsdemo.mobilebanking.domain.UserAccount;
import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountRequest;
import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AccountMapper {
    @Mapping(target = "accountType", ignore = true)
    Account mapRequestToEntity(AccountRequest accountRequest);

    @Mapping(target = "id", source = "userAccount.account.id")
    @Mapping(target = "accountNumber", source = "userAccount.account.accountNumber")
    @Mapping(target = "accountName", source = "userAccount.account.accountName")
    @Mapping(target = "accountBalance", source = "userAccount.account.accountBalance")
    @Mapping(target = "user", source = "userAccount.user", qualifiedByName = "toUserResponse")
    @Mapping(target = "accountType", source = "userAccount.account.accountType.name")
    AccountResponse mapUserAccountToAccountResponse(UserAccount userAccount);

    void setUserForAccountResponse(@MappingTarget AccountResponse accountResponse, User user);

}
