package kh.educat.cstad.aftmobilebankingapi.controller;

import kh.educat.cstad.aftmobilebankingapi.dto.CustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.UpdateCustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Allow frontend or browser to call API
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    //  Disable (soft delete) customer by phone number (PUT)
    @PutMapping("/{phoneNumber}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableCustomer(@PathVariable String phoneNumber) {
        customerService.disableByPhoneNumber(phoneNumber);
    }

    // GET all customers
    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findByAll();
    }

    //  Create new customer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@RequestBody CustomerRequest customerRequest) {
        return customerService.createNew(customerRequest);
    }

    //  Get customer by phone (browser-friendly)
    @GetMapping("/phone")
    public CustomerResponse findByPhone(@RequestParam String phone) {
        return customerService.findByPhoneNumber(phone);
    }

    //  Update customer by phone
    @PutMapping("/phone")
    public CustomerResponse updateByPhone(
            @RequestParam String phone,
            @RequestBody UpdateCustomerRequest updateCustomerRequest
    ) {
        return customerService.updateByPhoneNumber(phone, updateCustomerRequest);
    }

    // Simple test GET endpoint to check API from browser
    @GetMapping("/ping")
    public String ping() {
        return "Customer API is running!";
    }
}
