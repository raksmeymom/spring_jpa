package kh.educat.cstad.aftmobilebankingapi.mapper;

import kh.educat.cstad.aftmobilebankingapi.domain.Account;
import kh.educat.cstad.aftmobilebankingapi.dto.AccountResponse;
import kh.educat.cstad.aftmobilebankingapi.dto.CreateAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true) // Set manually in service
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    default Account fromCreateRequest(CreateAccountRequest createAccountRequest) {
        return null;
    }

    @Mapping(source = "customer.id", target = "customerId")
    AccountResponse toAccountResponse(Account account);
}
