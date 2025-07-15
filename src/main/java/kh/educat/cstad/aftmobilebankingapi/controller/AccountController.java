package kh.educat.cstad.aftmobilebankingapi.controller;

import kh.educat.cstad.aftmobilebankingapi.dto.AccountResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.CreateAccountRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.UpdateAccountRequest;
import kh.educat.cstad.aftmobilebankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@RequestBody CreateAccountRequest request) {
        return accountService.create(request);
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse findByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

    @PutMapping("/{accountNumber}")
    public AccountResponse update(@PathVariable String accountNumber,
                                  @RequestBody UpdateAccountRequest request) {
        return accountService.updateByAccountNumber(accountNumber, request);
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String accountNumber) {
        accountService.softDeleteByAccountNumber(accountNumber);
    }
}
