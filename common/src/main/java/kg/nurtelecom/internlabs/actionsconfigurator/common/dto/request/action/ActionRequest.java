package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.action;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation.StartEndDate;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.ActionType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@StartEndDate(
        startDate = "startDate",
        endDate = "endDate",
        message = "Action start date must be before end date and vice versa." // TODO: Add validated values of start and end date to message
)
public record ActionRequest(
        @Nullable
        Long id,

        @NotBlank(message = "Action's name cannot be blank or null.")
        @Size(
                max = Action.NAME_LENGTH,
                message = "Action's name '${validatedValue}' cannot be more then {max} symbols."
        )
        String name,

        @Size(
                max = Action.DESCRIPTION_LENGTH,
                message = "Action's description '${validatedValue}' cannot be more then {max} symbols."
        )
        String description,

        // TODO: Delete action type and replace it with many to many with tickets promos
        @NotNull(message = "Action's type cannot be null.")
        ActionType type,

        @NotNull(message = "Action's start date cannot be null.")
        @Future(message = "Action's start date '${validatedValue}' must start in future.")
        @DateTimeFormat(pattern = "YYYY-MM-DDTHH:MM:SS")
        LocalDateTime startDate,

        @NotNull(message = "Action's end date cannot be null.")
        @Future(message = "Action's end date '${validatedValue}' must end in future.")
        @DateTimeFormat(pattern = "YYYY-MM-DDTHH:MM:SS")
        LocalDateTime endDate,

        @Nullable
        Long ticketTemplateId,

        @Nullable
        Long promocodeTemplateId
) {

}
