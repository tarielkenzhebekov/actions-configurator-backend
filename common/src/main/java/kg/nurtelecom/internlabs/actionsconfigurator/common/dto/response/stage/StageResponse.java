package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.BaseResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@JsonPropertyOrder(
        {
                "id",
                "amount",
                "startDate",
                "endDate",
                "ticketLimit",
                "promocodeLimit",
                "actionId",
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
public class StageResponse extends BaseResponse {
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer ticketLimit;
    Integer promocodeLimit;
    Long actionId;
    Boolean activated;
}
