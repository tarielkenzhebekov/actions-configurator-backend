package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.BaseResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode.PromocodeResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket.TicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.ActionType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@JsonPropertyOrder(
        {
                "id",
                "name",
                "description",
                "startDate",
                "endDate",
                "type",
                "ticketTemplateId",
                "promocodeTemplateId",
                "activated",
                "deleted",
                "createdAt",
                "createdBy",
                "updatedAt",
                "updatedBy"
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class ActionResponse extends BaseResponse {
    String name;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    ActionType type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long ticketTemplateId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long promocodeTemplateId;
    Boolean activated;
}
