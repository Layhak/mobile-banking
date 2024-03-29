package co.istad.jbsdemo.mobilebanking.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users_tbl")
@Data
public class User {
    @ManyToMany
    Set<Role> roles;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;
    private String username;
    private  String fullName;
    private String gender;
    @Column(unique = true, nullable = false)
    private String pin;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String profileImage;
    private String phoneNumber;
    private String cityOrProvince;
    private String khanOrDistrict;
    private String sangkatOrCommune;
    private String employeeType;
    private String companyName;
    private String mainSourceOfIncome;
    private BigDecimal monthlyIncomeRange;
    private String oneSignalId;
    private String studentIdCard;
    private boolean isDeleted;//for statistic usage
    private boolean isBlocked;//disable
    private LocalDateTime created_at;
}
