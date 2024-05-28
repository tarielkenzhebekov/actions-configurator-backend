package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.rule;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation.MinMaxSum;

@MinMaxSum(
        minSum = "minSum",
        maxSum = "maxSum",
        message = "Rule's min sum must be less then max sum." // TODO: Add validated value in this message.
)
public record RuleRequest(
        @Nullable
        Long id,

        @NotNull(message = "Rule's minimal sum cannot be null.")
        @Positive(message = "Rule's minimal sum '${validatedValue}' must be not zero and positive.")
        Double minSum,

        @NotNull(message = "Rule's maximal sum cannot be null.")
        @Positive(message = "Rule's maximal sum '${validatedValue}' must be not zero and positive.")
        Double maxSum,

        @NotNull(message = "Rule's amount cannot be null.")
        @Positive(message = "Rule's amount '${validatedValue}' must be not zero and positive.")
        Long amount,

        @Nullable
        Long stageId
) {
}
