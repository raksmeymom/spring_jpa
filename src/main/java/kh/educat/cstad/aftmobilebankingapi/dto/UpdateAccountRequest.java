package kh.educat.cstad.aftmobilebankingapi.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateAccountRequest {
    private String accountName;
    private BigDecimal balance;
    private Integer accountTypeId;
}
