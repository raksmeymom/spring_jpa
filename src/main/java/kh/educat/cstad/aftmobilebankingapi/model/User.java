package kh.educat.cstad.aftmobilebankingapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Enumerated(EnumType.STRING)
        private AccountType accountType;

        @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
        private List<Transaction> sentTransactions;

        @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
        private List<Transaction> receivedTransactions;
    }

