package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.ticket;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation.StartEndDate;
import lombok.Builder;

import java.time.LocalDateTime;

@StartEndDate(
        startDate = "startDate",
        endDate = "endDate",
        message = "Ticket's start date must be before end date and vise versa."
)
@Builder
public record TicketRequest(
        @Nullable
        Long id,

        @NotNull(message = "Ticket's start date cannot be null.")
        @Future(message = "Ticket's start date '${validatedValue}' must end in future")
        LocalDateTime startDate,

        @NotNull(message = "Ticket's end date cannot be null.")
        @Future(message = "Ticket's end date '${validatedValue}' must end in future")
        LocalDateTime endDate,

        // TODO: Add logic for ticket rule bind
        @Nullable
        Long ruleId
){
}
