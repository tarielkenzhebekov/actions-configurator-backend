package kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.impl;

import jakarta.annotation.PostConstruct;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.CronJobService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.ExcelGenerator;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.ReportGenerator;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ReportGeneratorImpl implements ReportGenerator {
    ReportService reportService;
    ExcelGenerator excelGenerator;
    CronJobService cronJobService;

    Logger logger = LoggerFactory.getLogger(ReportGeneratorImpl.class);

    @NonFinal
    @Value("${application.report.file.path}")
    String path;

    @NonFinal
    @Value("${application.report.scheduler.cron}")
    String cron;

    @Override
    public void run() {
        String fileName = getFileName();
        boolean reportSaved = excelGenerator.generate(path, fileName);
        if (reportSaved) {
            int noOfAffectedRow = reportService.save(path, fileName);
            if (noOfAffectedRow != 1) {
                logger.error("Couldn't save report " + fileName + " in database");
            }
        }
    }

    @PostConstruct
    private void scheduleTask() {
        cronJobService.createCronJob(this, cron);
    }

    private String getFileName() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String dateTimeInfo = dateFormat.format(new Date());
        return String.format("Report_%s.xlsx", dateTimeInfo);
    }
}
