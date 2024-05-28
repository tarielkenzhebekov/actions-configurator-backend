package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.stage;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record StageRequest(
        @Nullable
        Long id,

        @NotNull(message = "Stage's start date cannot be null.")
        @Future(message = "Stage's start date '${validatedValue}' must end in future")
        @DateTimeFormat(pattern = "YYYY-MM-DD:THH:MM:SS")
        LocalDateTime startDate,

        @NotNull(message = "Stage's end date cannot be null.")
        @Future(message = "Stage's end date '${validatedValue}' must end in future")
        @DateTimeFormat(pattern = "YYYY-MM-DD:THH:MM:SS")
        LocalDateTime endDate,

        @NotNull(message = "Stage's ticket limit cannot be null.")
        @Positive(message = "Stage's ticket limit '${validatedValue}' must be positive and not zero.")
        Integer ticketLimit,

        @NotNull(message = "Stage's promocode limit cannot be null.")
        @Positive(message = "Stage's promocode limit '${validatedValue}' must be positive and not zero.")
        Integer promocodeLimit,

        @Nullable
        Long actionId
) {
}
