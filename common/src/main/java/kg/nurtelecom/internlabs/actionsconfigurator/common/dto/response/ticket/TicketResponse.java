package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.BaseResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@JsonPropertyOrder(
        {
                "id",
                "startDate",
                "endDate",
                "ruleId",
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
public class TicketResponse extends BaseResponse {
    LocalDate startDate;
    LocalDate endDate;
    Long ruleId;
}
