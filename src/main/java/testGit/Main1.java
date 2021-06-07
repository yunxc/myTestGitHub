package testGit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.*;

/**
 * @author yunlong.zhang
 * @date 2019/1/4 10:23
 */
public class Main1 {
    public static void main(String[] args) {

        Map<String, Integer> m = new HashMap<>();
        m.put("a",1);
        m.put("b",2);
        m.put("c",3);
        m.forEach((s, integer) -> {
            System.out.println("s" + s);
            System.out.println("integer" + integer);
        });

        System.out.println("Hello Git");
        List<String> strings = intervalTimeByMonth("2020-02", "2020-05");
    }

    /**
     * 按照月份分割一段时间
     *
     * @param startTime     开始时间戳(毫秒)
     * @param endTime       结束时间戳(毫秒)
     * @param beginDateList 开始段时间戳 和 结束段时间戳 一一对应
     * @param endDateList   结束段时间戳 和 开始段时间戳 一一对应
     */
    public static void getIntervalTimeByMonth(Long startTime, Long endTime, List<Long> beginDateList, List<Long> endDateList) {
        Date startDate = new Date(startTime);
        Date endDate = new Date(endTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        beginDateList.add(calendar.getTimeInMillis());
        while (calendar.getTimeInMillis() < endDate.getTime()) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 13);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            if(calendar.getTimeInMillis() < endDate.getTime()){
                endDateList.add(calendar.getTimeInMillis());
            } else {
                endDateList.add(endDate.getTime());
                break;
            }
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            beginDateList.add(calendar.getTimeInMillis());
        }
    }

    private static List<String> intervalTimeByMonth(String startDate, String endDate){
        startDate = startDate + "-01";
        endDate = endDate + "-01";
        List<String> li = new ArrayList<>();
        DateTimeFormatter inDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate startLocalDate=LocalDate.parse(startDate, inDateTimeFormatter);
        //开始时间
        li.add(startLocalDate.format(outDateTimeFormatter));
        LocalDate endLocalDate=LocalDate.parse(endDate, inDateTimeFormatter);
        LocalDate _currentLocalDate=LocalDate.parse(startDate, inDateTimeFormatter);
        while (_currentLocalDate.isBefore(endLocalDate)){
            _currentLocalDate = _currentLocalDate.plusMonths(1);
            li.add(_currentLocalDate.format(outDateTimeFormatter));
        }
        return li;
    }

}
