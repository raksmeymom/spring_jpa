package kh.educat.cstad.aftmobilebankingapi.service;

import kh.educat.cstad.aftmobilebankingapi.domain.Account;
import kh.educat.cstad.aftmobilebankingapi.domain.AccountType;
import kh.educat.cstad.aftmobilebankingapi.domain.Customer;
import kh.educat.cstad.aftmobilebankingapi.dto.AccountResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.CreateAccountRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.UpdateAccountRequest;
import kh.educat.cstad.aftmobilebankingapi.mapper.AccountMapper;
import kh.educat.cstad.aftmobilebankingapi.repository.AccountRepository;
import kh.educat.cstad.aftmobilebankingapi.repository.AccountTypeRepository;
import kh.educat.cstad.aftmobilebankingapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public AccountResponse create(CreateAccountRequest request) {
        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        AccountType accountType = accountTypeRepository.findById(request.getAccountTypeId())
                .orElseThrow(() -> new RuntimeException("AccountType not found"));

        Account account = accountMapper.fromCreateRequest(request);
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setIsDeleted(false);

        Account savedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(savedAccount);
    }

    public List<AccountResponse> findAll() {
        return accountRepository.findAllByIsDeletedFalse()
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    public AccountResponse findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumberAndIsDeletedFalse(accountNumber)
                .map(accountMapper::toAccountResponse)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional
    public AccountResponse updateByAccountNumber(String accountNumber, UpdateAccountRequest request) {
        Account account = accountRepository.findByAccountNumberAndIsDeletedFalse(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountMapper.updateAccountFromDto(request, account);

        if (request.getAccountTypeId() != null) {
            AccountType accountType = accountTypeRepository.findById(request.getAccountTypeId())
                    .orElseThrow(() -> new RuntimeException("AccountType not found"));
            account.setAccountType(accountType);
        }

        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(updatedAccount);
    }

    @Transactional
    public void softDeleteByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumberAndIsDeletedFalse(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setIsDeleted(true);
        accountRepository.save(account);
    }
}
