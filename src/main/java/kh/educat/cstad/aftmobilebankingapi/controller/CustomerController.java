package kh.educat.cstad.aftmobilebankingapi.controller;

import kh.educat.cstad.aftmobilebankingapi.dto.CustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;
import kh.educat.cstad.aftmobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping
    public List<CustomerResponse> findByAll() {
        return customerService.findByAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerResponse create(@RequestBody CustomerRequest createCustomerRequest) {
        return customerService.createNew(createCustomerRequest);
    }
}
