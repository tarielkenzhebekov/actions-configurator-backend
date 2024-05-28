package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.statistics;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.statistics.StatisticsService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics.StatisticsByActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics.StatisticsOverallResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StatisticsController {
    StatisticsService statisticsService;

    @GetMapping("/overall")
    public ResponseEntity<StatisticsOverallResponse> getOverall() {
        StatisticsOverallResponse data = statisticsService.getOverall();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/action")
    public ResponseEntity<List<StatisticsByActionResponse>> getByAction(
            @RequestParam Long id,
            @RequestParam String type) {
        List<StatisticsByActionResponse> data = statisticsService.getByAction(id, type);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
