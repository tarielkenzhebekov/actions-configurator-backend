package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.actionconfiguration;

import jakarta.validation.constraints.NotNull;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.rule.RuleRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.stage.StageRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StageConfigurationRequest {

    @NotNull(message = "Action configuration must contain stage.")
    StageRequest stage;

    @NotNull(message = "Action configuration must contain rules.")
    List<RuleRequest> rules;


}
