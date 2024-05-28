package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule.RuleResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage.StageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StageConfigurationResponse {

    StageResponse stage;
    List<RuleResponse> rules;

}
