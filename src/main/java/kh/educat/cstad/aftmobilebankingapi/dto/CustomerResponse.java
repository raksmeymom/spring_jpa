package kh.educat.cstad.aftmobilebankingapi.dto;

import lombok.Builder;
@Builder
public record CustomerResponse(
        String fullname,
        String gender,
        String email
) {
}
