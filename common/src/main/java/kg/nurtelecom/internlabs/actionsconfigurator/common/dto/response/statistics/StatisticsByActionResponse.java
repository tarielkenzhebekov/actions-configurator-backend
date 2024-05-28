package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics;

import lombok.Builder;

@Builder
public record StatisticsByActionResponse(
        Long amount,
        Long sold,
        Long income,
        Long used
) {
}
