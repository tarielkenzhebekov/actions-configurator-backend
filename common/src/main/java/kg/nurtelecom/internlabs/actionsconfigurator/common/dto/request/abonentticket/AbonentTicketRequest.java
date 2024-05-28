package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentticket;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

public record AbonentTicketRequest(

        @NotNull(message = "Ticket ID cannot be null.")
        Long ticketId,

        @NotNull(message = "Sum cannot be null.")
        @Positive(message = "Sum must be positive number.")
        Long sum,

        @NotNull(message = "Phone number cannot be null.")
        String abonentPhoneNumber

) {
}
