package com.newlecmineursprj.util;




import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class RadioButtonRegDate {
    public static List<String> regDateList() {
        return Arrays.asList("오늘","3일","7일","1개월","3개월","1년","전체");
    }

    public static String getStartDate() {
        LocalDate startDate = LocalDate.now();
        return  startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String regDatesForSearch(String buttonRegDate) {
        LocalDate now = LocalDate.now();
        return switch (buttonRegDate) {
            case "오늘" -> now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case "3일" -> now.minusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case "7일" -> now.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case "1개월" -> now.minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case "3개월" -> now.minusMonths(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case "1년" -> now.minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            default -> now.minusYears(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        };
    }
}
