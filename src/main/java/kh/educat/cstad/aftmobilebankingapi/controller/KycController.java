package kh.educat.cstad.aftmobilebankingapi.controller;

import kh.educat.cstad.aftmobilebankingapi.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class KycController {
    private final KycService kycService;

    @PutMapping("/verify/{id}")
    public ResponseEntity<String> verifyKyc(@PathVariable Integer id) {
        kycService.verifyById(id);
        return ResponseEntity.ok("KYC verified successfully");
    }
}
