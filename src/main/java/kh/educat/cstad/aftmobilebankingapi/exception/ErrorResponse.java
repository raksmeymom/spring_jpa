package kh.educat.cstad.aftmobilebankingapi.exception;

import jakarta.validation.constraints.AssertFalse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record  ErrorResponse<T>(
        String message,
        Integer code,
        LocalDateTime timestamp,
        T details
        ){


}




