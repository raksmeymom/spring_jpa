package kh.educat.cstad.aftmobilebankingapi.dto;

public record CustomerRequest(
        String fullName,
        String gender,
        String email,
        String phoneNumber,
        String remark
) {
}
