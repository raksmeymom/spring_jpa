package kh.educat.cstad.aftmobilebankingapi.service;

import kh.educat.cstad.aftmobilebankingapi.dto.CustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.UpdateCustomerRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    @Transactional
    void disableBYPhoneNumber(String phoneNumber);

    List<CustomerResponse> findByAll();
    CustomerResponse createNew(CustomerRequest customerRequest);
    CustomerResponse findByPhoneNumber(String phoneNumber);
    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

    void disableByPhoneNumber(String phoneNumber);
}
