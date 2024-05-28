package kg.nurtelecom.internlabs.actionsconfigurator.api.service.statistics;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics.StatisticsByActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics.StatisticsOverallResponse;

import java.util.List;

public interface StatisticsService {

    StatisticsOverallResponse getOverall();

    List<StatisticsByActionResponse> getByAction(Long id, String type);
}