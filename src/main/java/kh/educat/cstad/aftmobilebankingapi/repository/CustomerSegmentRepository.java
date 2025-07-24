package kh.educat.cstad.aftmobilebankingapi.repository;


import kh.educat.cstad.aftmobilebankingapi.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerSegmentRepository extends
        JpaRepository<CustomerSegment, Integer> {

    Optional<CustomerSegment> findBySegment(String s);

}
