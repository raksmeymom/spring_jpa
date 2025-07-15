package kh.educat.cstad.aftmobilebankingapi.service;

import kh.educat.cstad.aftmobilebankingapi.domain.Account;
import kh.educat.cstad.aftmobilebankingapi.domain.Customer;
import kh.educat.cstad.aftmobilebankingapi.dto.AccountResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.CreateAccountRequest;
import kh.educat.cstad.aftmobilebankingapi.mapper.AccountMapper;
import kh.educat.cstad.aftmobilebankingapi.repository.AccountRepository;
import kh.educat.cstad.aftmobilebankingapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    public AccountResponse createAccount(CreateAccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Account account = accountMapper.fromCreateRequest(request);
        account.setCustomer(customer);

        String segmentName = customer.getCustomerSegment().getName().toUpperCase();

        switch (segmentName) {
            case "GOLD" -> account.setOverLimit(BigDecimal.valueOf(50000));
            case "SILVER" -> account.setOverLimit(BigDecimal.valueOf(10000));
            case "REGULAR" -> account.setOverLimit(BigDecimal.valueOf(5000));
            default -> account.setOverLimit(BigDecimal.ZERO);
        }

        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }
}
