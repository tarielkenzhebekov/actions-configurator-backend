package kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob;

import java.time.LocalDateTime;

public interface CronJobService {

    void createCronJob(Runnable task, LocalDateTime dateTime);

    void createCronJob(Runnable task, String cronExpression);

}
