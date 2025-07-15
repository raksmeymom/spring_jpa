package kh.educat.cstad.aftmobilebankingapi.service;

import kh.educat.cstad.aftmobilebankingapi.domain.Customer;
import kh.educat.cstad.aftmobilebankingapi.domain.KYC;
import kh.educat.cstad.aftmobilebankingapi.repository.KYCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KycService {
    private final KYCRepository kycRepository;

    public void createForCustomer(Customer customer) {
        KYC kyc = new KYC();
        kyc.setCustomer(customer);
        kyc.setVerified(false);
        kycRepository.save(kyc);
    }

    public void verifyById(Integer id) {
        KYC kyc = kycRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KYC not found"));
        kyc.setVerified(true);
        kycRepository.save(kyc);
    }
}