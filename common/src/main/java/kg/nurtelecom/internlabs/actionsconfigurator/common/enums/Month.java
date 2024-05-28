package kg.nurtelecom.internlabs.actionsconfigurator.common.enums;

import lombok.Getter;

import java.time.LocalDateTime;

public enum Month {
    JAN("JAN", 1, 31),
    FEB("FEB", 2, 28, 29),
    MAR("MAR", 3, 31),
    APR("APR", 4, 30),
    MAY("MAY", 5, 31),
    JUN("JUN", 6, 30),
    JUL("JUL", 7, 31),
    AUG("AUG", 8, 31),
    SEP("SEP", 9, 30),
    OCT("OCT", 10, 31),
    NOV("NOV", 11, 30),
    DEC("DEC", 12, 31);

    @Getter
    private final String name;
    @Getter
    private final int number;
    private final int daysNormal;
    private final int daysLeap;

    Month(String name, int number, int daysNormal) {
        this(name, number, daysNormal, daysNormal);
    }

    Month(String name, int number, int daysNormal, int daysLeap) {
        this.name = name;
        this.number = number;
        this.daysNormal = daysNormal;
        this.daysLeap = daysLeap;
    }

    public int getDays() {
        if (isThisYearLeap()) {
            return daysLeap;
        } else {
            return daysNormal;
        }
    }

    private boolean isThisYearLeap() {
        return isLeapYear(
                LocalDateTime.now().getYear()
        );
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }
}
