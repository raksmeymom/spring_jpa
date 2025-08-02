package kh.educat.cstad.aftmobilebankingapi.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        String name,
        String extension,
        String mimeTypeFile,
        String uri,
        Long size
) {
}