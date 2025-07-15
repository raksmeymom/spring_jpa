package kh.educat.cstad.aftmobilebankingapi.repository;

import kh.educat.cstad.aftmobilebankingapi.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface KYCRepository extends JpaRepository<KYC, Integer> {


    Optional<KYC> findByCustomerId(Integer custoomerID);
}
