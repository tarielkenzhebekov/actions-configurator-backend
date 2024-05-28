package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics;

import lombok.Builder;

@Builder
public record StatisticsOverallResponse(
        Long actionsAmount,
        Long activatedActionsAmount,
        Long totalIncome,
        Long promocodesIncome,
        Long ticketsIncome,
        Long promocodesAmount,
        Long ticketsAmount,
        Long promocodesSold,
        Long ticketsSold,
        Long promocodesUsed,
        Long ticketsUsed
) {

}