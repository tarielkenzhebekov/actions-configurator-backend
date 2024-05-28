package kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.CronJobService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.LocalDateTimeCronParser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("cronJobService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CronJobServiceImpl implements CronJobService {

    TaskScheduler taskScheduler;

    LocalDateTimeCronParser localDateTimeCronParser;

    @Override
    public void createCronJob(Runnable task, LocalDateTime dateTime) {
        createCronJob(
                task,
                localDateTimeCronParser.parse(dateTime)
        );
    }

    @Override
    public void createCronJob(Runnable task, String cronExpression) {
        taskScheduler.schedule(
                task,
                new CronTrigger(cronExpression)
        );
    }
}
