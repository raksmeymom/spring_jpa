package kh.educat.cstad.aftmobilebankingapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private String accountNumber;
    private BigDecimal balance;
    private Integer customerId;
}
