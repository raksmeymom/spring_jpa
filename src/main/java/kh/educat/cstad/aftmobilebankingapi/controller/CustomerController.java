package kh.educat.cstad.aftmobilebankingapi.controller;

import kh.educat.cstad.aftmobilebankingapi.dto.CustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.UpdateCustomerRequest;
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

    @PutMapping("/{phoneNumber}")
    public void disableCustomer(@PathVariable("phoneNumber") String phoneNumber){
        customerService.disableByPhoneNumber(phoneNumber);
    }


    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findByAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@RequestBody CustomerRequest customerRequest) {
        return customerService.createNew(customerRequest);
    }

    @GetMapping("/phone")
    public CustomerResponse findByPhone(@RequestParam String phone) {
        return customerService.findByPhoneNumber(phone);
    }

    @PutMapping("/phone")
    public CustomerResponse updateByPhone(
            @RequestParam String phone,
            @RequestBody UpdateCustomerRequest updateCustomerRequest
    ) {
        return customerService.updateByPhoneNumber(phone, updateCustomerRequest);
    }
}
