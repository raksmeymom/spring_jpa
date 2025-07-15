package kh.educat.cstad.aftmobilebankingapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponse {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal overLimit;
    private Integer customerId;
}
