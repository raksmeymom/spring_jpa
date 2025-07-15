package kh.educat.cstad.aftmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class KYC {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid; // Using UUID as primary key

    @Column(unique = true, nullable = false, length = 12)
    private String nationalCardId; // ✅ fixed typo

    @Column(nullable = false)
    private Boolean isVerified = false;

    @Column(nullable = false)
    private Boolean isDelete = false;

    @OneToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // ✅ Proper setter
    public void setVerified(boolean verified) {
        this.isVerified = verified;
    }
}
