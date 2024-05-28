package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentpromocode;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

public record AbonentPromocodeRequest(

        @NotNull(message = "Promocode ID cannot be null.")
        Long promocodeId,

        @NotNull(message = "Sum cannot be null.")
        @Positive(message = "Sum must be positive.")
        Long sum,

        @NotNull(message = "Abonent phone number cannot be null.")
        String abonentPhoneNumber

) {

}
