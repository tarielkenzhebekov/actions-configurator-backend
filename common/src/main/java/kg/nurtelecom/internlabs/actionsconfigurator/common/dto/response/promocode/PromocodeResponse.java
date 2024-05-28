package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.BaseResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@JsonPropertyOrder(
        {
                "id",
                "expiredDate",
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
public class PromocodeResponse extends BaseResponse {
    LocalDateTime expiredDate;
    Long ruleId;
}
