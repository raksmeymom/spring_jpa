package kh.educat.cstad.aftmobilebankingapi.repository;

import kh.educat.cstad.aftmobilebankingapi.domain.Customer;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByIsDeletedFalse();


    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.isDeleted = true WHERE c.phoneNumber = ?1")

    void disableByPhoneNumber(String phoneNumber);

    Optional<Customer> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    Optional<Customer> findByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
