package kh.educat.cstad.aftmobilebankingapi.init;

import jakarta.annotation.PostConstruct;

import kh.educat.cstad.aftmobilebankingapi.domain.CustomerSegment;
import kh.educat.cstad.aftmobilebankingapi.repository.CustomerSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerSegmentInitialize {

    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void init() {
        if (customerSegmentRepository.count() == 0) {
            CustomerSegment regular = new CustomerSegment();
            regular.setSegment("REGULAR");
            regular.setIsDeleted(false);
            regular.setDescription("Regular segment");

            CustomerSegment silver = new CustomerSegment();
            silver.setSegment("SILVER");
            silver.setIsDeleted(false);
            silver.setDescription("Silver segment");

            CustomerSegment gold = new CustomerSegment();
            gold.setSegment("GOLD");
            gold.setIsDeleted(false);
            gold.setDescription("Gold segment");

            customerSegmentRepository.saveAll(
                    List.of(regular, silver, gold)
            );
        }
    }

}
