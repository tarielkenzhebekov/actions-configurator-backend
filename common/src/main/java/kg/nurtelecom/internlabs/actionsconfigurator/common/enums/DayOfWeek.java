package kg.nurtelecom.internlabs.actionsconfigurator.common.enums;

import lombok.Getter;

@Getter
public enum DayOfWeek {
    MONDAY("MON", 1),
    TUESDAY("TUE", 2),
    WEDNESDAY("WED", 3),
    THURSDAY("THU", 4),
    FRIDAY("FRI", 5),
    SATURDAY("SAT", 6),
    SUNDAY("SUN", 7);

    private final String name;
    private final int number;

    DayOfWeek(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
