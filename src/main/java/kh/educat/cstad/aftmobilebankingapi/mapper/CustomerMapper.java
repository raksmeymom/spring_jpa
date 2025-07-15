package kh.educat.cstad.aftmobilebankingapi.mapper;

import kh.educat.cstad.aftmobilebankingapi.domain.Customer;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.CustomerResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.UpdateCustomerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer fromCustomerRequest(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerPartially(UpdateCustomerRequest dto, @MappingTarget Customer customer);
}
