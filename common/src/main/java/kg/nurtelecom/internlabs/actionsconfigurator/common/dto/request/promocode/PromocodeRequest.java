package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.promocode;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record PromocodeRequest(
        @Nullable
        Long id,

        @NotNull(message = "Promocode's expiration date cannot be null.")
        @Future(message = "Promocode's expiration date '${validatedValue}' must end in future")
        @DateTimeFormat(pattern = "YYYY-MM-DDTHH:MM:SS")
        LocalDateTime expiredDate,

        @Nullable
        Long ruleId // TODO: Add logic to service
) {
}
