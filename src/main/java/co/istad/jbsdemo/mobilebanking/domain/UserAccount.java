package co.istad.jbsdemo.mobilebanking.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Entity(name = "user_account_tbl")
@Data
@Accessors(chain = true)
public class UserAccount {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Timestamp createdAt;
    private boolean isDisabled;

}
