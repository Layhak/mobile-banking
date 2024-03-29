package co.istad.jbsdemo.mobilebanking.feature.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record UserRequest(
        @NotEmpty
        @NotNull
        @Column(unique = true, nullable = false)
        String username,
        @NotEmpty @NotNull
        String fullName,
        @NotNull
        @NotEmpty
        @Column(nullable = false)
        String gender,
        @Pattern(regexp = "\\d+", message = "Pin must be number")
        @Size(max = 6, min = 6, message = "Pin must be 6 degits")
        String pin,
        @Email(message = "Email format is not correct!")
        String email,
        @Column(nullable = false)
        String password,
        String profileImage,
        @Column(unique = true, nullable = false)
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String employeeType,
        String companyName,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange,
        String studentIdCard,
        List<String> roles
) {

}
