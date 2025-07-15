package kh.educat.cstad.aftmobilebankingapi.service.impl;

import kh.educat.cstad.aftmobilebankingapi.domain.Customer;
import kh.educat.cstad.aftmobilebankingapi.dto.*;
import kh.educat.cstad.aftmobilebankingapi.mapper.CustomerMapper;
import kh.educat.cstad.aftmobilebankingapi.repository.CustomerRepository;
import kh.educat.cstad.aftmobilebankingapi.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Transactional
    @Override
    public void disableBYPhoneNumber(String phoneNumber){
        if (!customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer Phone Number Not Found");
        }
        customerRepository.disableByPhoneNumber (phoneNumber);
    }



    @Override
    public List<CustomerResponse> findByAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse createNew(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        if (customerRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }

        Customer customer = customerMapper.fromCustomerRequest(request);
        customer.setIsDeleted(false);
        log.info("Saving customer: {}", customer.getFullName());
        customer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumber(phoneNumber)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found"));
    }

    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateRequest) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found"));

        customerMapper.updateCustomerPartially(updateRequest, customer);
        customer = customerRepository
                .save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public void disableByPhoneNumber(String phoneNumber) {

        if (!customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found");
        }
        customerRepository.disableByPhoneNumber(phoneNumber);

    }
}
