package com.newlecmineursprj.util;



import org.apache.poi.ss.usermodel.DateUtil;
import org.thymeleaf.util.DateUtils;

import java.util.*;

public abstract class RadioButtonRegDate {
    public static List<String> regDateList() {
        return Arrays.asList("오늘","3일","7일","1개월","1년","전체");
    }

    public static Date regDatesForSearch(String searchRegDate) {
        Date startDate = null;
        switch (searchRegDate) {
            case "오늘" :
                startDate =
                break;
            case "3일" :
                startDate = DateUtils.
        }
    }
}
