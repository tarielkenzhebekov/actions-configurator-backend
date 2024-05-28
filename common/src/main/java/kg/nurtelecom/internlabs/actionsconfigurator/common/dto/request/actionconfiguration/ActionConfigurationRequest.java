package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.actionconfiguration;

import jakarta.validation.constraints.NotNull;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.action.ActionRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.promocode.PromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.ticket.TicketRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActionConfigurationRequest {

    @NotNull(message = "Action configuration must contain action.")
    ActionRequest action;

    TicketRequest ticketTemplate;

    PromocodeRequest promocodeTemplate;

    @NotNull(message = "Action configuration must contain stages.")
    List<StageConfigurationRequest> stages;

}
