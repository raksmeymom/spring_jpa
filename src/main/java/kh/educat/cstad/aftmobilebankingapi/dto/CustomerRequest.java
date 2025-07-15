package kh.educat.cstad.aftmobilebankingapi.dto;

public record CustomerRequest(
        String fullName,
        String email,
        String phoneNumber
) {}
