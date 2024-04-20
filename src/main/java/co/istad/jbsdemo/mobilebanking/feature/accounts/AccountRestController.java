package co.istad.jbsdemo.mobilebanking.feature.accounts;

import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountRequest;
import co.istad.jbsdemo.mobilebanking.feature.accounts.dto.AccountResponse;
import co.istad.jbsdemo.mobilebanking.utilities.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountRestController {
    private final AccountService accountService;

    @GetMapping
    @Operation(summary = "Get all account")
    public BaseResponse<List<AccountResponse>> getAllAccount() {
        return BaseResponse.<List<AccountResponse>>ok().setPayload(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by id")
    public BaseResponse<AccountResponse> getAccountById(
            @Parameter(
                    description = "Account ID",
                    required = true,
                    example = "Hello world"
            )
            @PathVariable(name = "id") String id) {

        return BaseResponse.<AccountResponse>ok().setPayload(accountService.findByAccountId(id));
    }

    @GetMapping("/account-number/{id}")
    @Operation(summary = "Get account by account number")
    public BaseResponse<AccountResponse> getAccountByAccountNumber(
            @Parameter(
                    description = "Account number",
                    required = true,
                    example = "String1"
            )
            @PathVariable(name = "id") String accountNumber) {

        return BaseResponse.<AccountResponse>ok().setPayload(accountService.findAccountByAccountNumber(accountNumber));
    }


    @GetMapping("/user/{id}")
    @Operation(summary = "Get account by user id")
    public BaseResponse<List<AccountResponse>> getAccountByUserId(
            @Parameter(
                    description = "User id",
                    required = true,
                    example = "64fbedab-3a14-4cc3-a1a8-2b11af8816f7"
            )
            @PathVariable(name = "id") String userid) {
        return BaseResponse.<List<AccountResponse>>ok().setPayload(accountService.findAccountByUserId(userid));
    }

    @PostMapping
    @Operation(summary = "Create account")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return BaseResponse.<AccountResponse>createSuccess().setPayload(accountService.createAccount(request));
    }

    @PatchMapping("/disable/{id}")
    @Operation(summary = "disable id by id")
    public BaseResponse<AccountResponse> disableUserAccountById(
            @Parameter(
                    description = "Disable User by ID",
                    required = true,
                    example = "2a6772e4-cbc3-4917-9c53-2630a5633c89"
            )
            @PathVariable("id") String id) {
        return BaseResponse.<AccountResponse>ok().setPayload(accountService.disableAccountById(id));
    }
    @PatchMapping("/enable/{id}")

    @Operation(summary = "disable id by id")
    public BaseResponse<AccountResponse> enableAccountById(
            @Parameter(
                    description = "Disable User by ID",
                    required = true,
                    example = "2a6772e4-cbc3-4917-9c53-2630a5633c89"
            )
            @PathVariable("id") String id) {
        return BaseResponse.<AccountResponse>ok().setPayload(accountService.enableAccountById(id));
    }
}
