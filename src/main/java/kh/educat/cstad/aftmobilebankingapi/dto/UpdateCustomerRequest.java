package kh.educat.cstad.aftmobilebankingapi.dto;

public record UpdateCustomerRequest(
        String fullName,
        String email,
        String phoneNumber
) {}
