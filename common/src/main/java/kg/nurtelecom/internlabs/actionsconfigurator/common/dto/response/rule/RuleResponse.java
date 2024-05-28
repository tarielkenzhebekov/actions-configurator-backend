package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.BaseResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage.StageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder(
        {
                "id",
                "minSum",
                "maxSum",
                "amount",
                "stageId",
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
public class RuleResponse extends BaseResponse {
    Double minSum;
    Double maxSum;
    Long amount;
    Long stageId;
}
