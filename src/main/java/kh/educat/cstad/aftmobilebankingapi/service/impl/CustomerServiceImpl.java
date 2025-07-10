package kh.educat.cstad.aftmobilebankingapi.service.impl;

import kh.educat.cstad.aftmobilebankingapi.domain.Customer;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;
import kh.educat.cstad.aftmobilebankingapi.repository.CustomerRepository;
//import kh.educat.cstad.aftmobilebankingapi.model.Customer;

import kh.educat.cstad.aftmobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public  class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public List<CustomerResponse> findByAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> CustomerResponse.builder()
                        .fullname(customer.getFullname())
                        .gender(customer.getGender())
                        .email(customer.getEmail())
                        .build()
                )
                .toList();
    }

    @Override
    public CustomerResponse createNew(CustomerRequest createCustomerRequest) {
        if (customerRepository.existsByEmail(createCustomerRequest.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");

        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");

        Customer customer = new Customer();
        customer.setFullname(createCustomerRequest.fullname());
        customer.setGender(createCustomerRequest.gender());
        customer.setEmail(createCustomerRequest.email());
        customer.setPhoneNumber(createCustomerRequest.phoneNumber());
        customer.setRemark(createCustomerRequest.remark());
        customer.setIsDeleted(false);

        log.info("Customer ID before save: {}", customer.getId());
        customer  = customerRepository.save(customer);
        log.info("Customer ID after save: {}", customer.getId());



        return CustomerResponse.builder()
                .fullname(customer.getFullname())
                .gender(customer.getGender())
                .email(customer.getEmail())
                .build();
    }
}
