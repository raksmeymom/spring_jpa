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
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(length = 15, nullable = false)
    private String gender;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false, unique = true)
    private String nationalCardId;

    // One customer can have many accounts
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    // One-to-One relationship with KYC
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private KYC kyc;

    // Segment (Gold, Silver, Regular)
    @ManyToOne
    private CustomerSegment customerSegment;

    public int getSegment() {
        return customerSegment != null ? customerSegment.getId() : 0;
    }
}
