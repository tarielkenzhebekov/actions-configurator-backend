package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode.PromocodeResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket.TicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.user.UserResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.serialization.ActionConfigurationResponseSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@JsonSerialize(using = ActionConfigurationResponseSerializer.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActionConfigurationResponse {

    ActionResponse action;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    TicketResponse ticketTemplate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    PromocodeResponse promcoodeTemplate;

    List<StageConfigurationResponse> stages;

    LocalDateTime createdAt;

    UserResponse createdBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserResponse updatedBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime updatedAt;

}
