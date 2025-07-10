package kh.educat.cstad.aftmobilebankingapi.service;

import kh.educat.cstad.aftmobilebankingapi.dto.CustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> findByAll();
    CustomerResponse createNew(CustomerRequest createCustomerRequest);



}
