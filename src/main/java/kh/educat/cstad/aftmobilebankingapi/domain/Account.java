package kh.educat.cstad.aftmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String actNo;
    private BigDecimal balance;
    private BigDecimal overLimit;

    @Column(nullable = false)
    private Boolean isDeleted;



    @ManyToOne
    @JoinColumn(name= "cust_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    private AccountType accountType;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(nullable = false, unique = true)
//    private String actNo;
//
//    @Column(nullable = false)
//    private String accountName;
//
//    @Column(nullable = false)
//    private BigDecimal balance;
//
//    @ManyToOne
//    @JoinColumn(name = "cust_id", nullable = false)
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name = "acc_type_id", nullable = false)
//    private AccountType accountType;
//
//    @Column(name = "is_deleted")
//    private Boolean isDeleted = false;
//
//    public void setDeleted(boolean deleted) {
//        this.isDeleted = deleted;
//    }
}
