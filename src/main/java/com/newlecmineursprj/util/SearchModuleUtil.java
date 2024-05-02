package com.newlecmineursprj.util;




import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class SearchModuleUtil {
    
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static List<String> DisplayStatusList() {
        return Arrays.asList("전체","진열함","진열안함");
    }
    public static Integer searchByDisplayStatus(String selectedDisplayStatus) {
        return switch (selectedDisplayStatus) {
            case "진열함" -> 1;
            case "진열안함" -> 0;
            default -> null;
        };
    }
    public static String getStartDate() {
        LocalDate startDate = LocalDate.now();
        return  startDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static List<String> regDateList() {
        return Arrays.asList("오늘","3일","7일","1개월","3개월","1년","전체");
    }
    public static String searchByRegDate(String buttonRegDate) {
        LocalDate now = LocalDate.now();
        return switch (buttonRegDate) {
            case "오늘" -> now.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            case "3일" -> now.minusDays(3).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            case "7일" -> now.minusDays(7).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            case "1개월" -> now.minusMonths(1).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            case "3개월" -> now.minusMonths(3).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            case "1년" -> now.minusYears(1).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            default -> now.minusYears(3).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        };
    }

}
