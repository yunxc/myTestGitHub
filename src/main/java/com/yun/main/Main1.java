package com.yun.main;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @author z
 * @date 2019/3/26 11:26
 */
public class Main1 {

    public static void main(String[] args) {
        StringJoiner s = new StringJoiner("A","B","M");
        s.add("1");
        s.add("2");
        s.add("3");

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月："+mon);

        DateTimeFormatter sdf =  DateTimeFormatter.ofPattern("yyyy年MM月");
        String format1 = YearMonth.now().minusMonths(1).format(sdf);

        System.out.println(format1);


        BigDecimal bigDecimal = BigDecimal.valueOf(15268811);
        BigDecimal divide = bigDecimal.divide(bigDecimal, 2);
        System.out.println(divide);

    }

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
