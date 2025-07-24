package kh.educat.cstad.aftmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account_types")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Changed 'type' to 'name' for clarity
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted;


    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;


    public void setType(String payroll) {

    }
}
