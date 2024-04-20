package co.istad.jbsdemo.mobilebanking.feature.accounts.dto;

import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserResponse;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponse(
        String id,
        String accountNumber,
        String accountName,
        BigDecimal accountBalance,
        UserResponse user,
        String accountType
) {
}
