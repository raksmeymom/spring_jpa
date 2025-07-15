package kh.educat.cstad.aftmobilebankingapi.mapper;

import kh.educat.cstad.aftmobilebankingapi.domain.Account;
import kh.educat.cstad.aftmobilebankingapi.dto.AccountResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.CreateAccountRequest;
import kh.educat.cstad.aftmobilebankingapi.dto.UpdateAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    Account fromCreateRequest(CreateAccountRequest createAccountRequest);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "accountType.name", target = "accountTypeName")
    AccountResponse toAccountResponse(Account account);

    void updateAccountFromDto(UpdateAccountRequest request, @MappingTarget Account account);
}
