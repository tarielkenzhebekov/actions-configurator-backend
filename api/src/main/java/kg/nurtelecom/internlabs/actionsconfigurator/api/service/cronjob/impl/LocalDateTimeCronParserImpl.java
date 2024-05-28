package kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.LocalDateTimeCronParser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LocalDateTimeCronParserImpl implements LocalDateTimeCronParser {



    @Override
    public String parse(LocalDateTime dateTime) {
        int second = dateTime.getSecond();
        int minute = dateTime.getMinute();
        int hour = dateTime.getHour();
        int month = dateTime.getMonth().getValue();
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        int dayOfMonth = dateTime.getDayOfMonth();

        return String.format("%d %d %d %d %d %d", second, minute, hour, month, dayOfWeek, dayOfMonth);
    }
}
