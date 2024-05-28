package kg.nurtelecom.internlabs.actionsconfigurator.api.startup.scheduling;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.actionconfiguration.ActionConfiguratorManagerService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.CronJobService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class JobConfig {

    CronJobService cronJobService;
    ActionConfiguratorManagerService actionConfiguratorManagerService;

    @Scheduled(cron = "0 0 0 1 1 *")
    private void eachYear() {
        // TODO: Add logic for starting action configuration per year. Why? Because cron work only for current year.
    }

}
