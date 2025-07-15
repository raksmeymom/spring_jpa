// AccountResponse.java
package kh.educat.cstad.aftmobilebankingapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal overLimit;
    private Integer customerId;
    private String accountTypeName;
}
