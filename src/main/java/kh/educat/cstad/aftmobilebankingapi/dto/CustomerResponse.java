package kh.educat.cstad.aftmobilebankingapi.dto;

import lombok.Builder;

@Builder
public record CustomerResponse(
        Long id,
        String fullName,
        String email,
        String phoneNumber
) {}
