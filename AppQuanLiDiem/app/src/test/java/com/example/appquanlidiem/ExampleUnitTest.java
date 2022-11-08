package com.example.appquanlidiem;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import com.example.appquanlidiem.phat_tkb_database.Utils;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import static org.junit.Assert.assertEquals;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int dayOfWeek=calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek--;
        calendar.add(Calendar.DATE,7-dayOfWeek);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        System.out.println("Lịch để có ngày hiện tại"+year+"năm"+month+"tháng"+day+"ngày"+hour+":"+minute+":"+second);
        System.out.println(dayOfWeek);
    }

    @Test
    public void autoTestgetStringFromWeekOfTerm() {
        int max = 0x1ffffff + 1;
        int maxWeekNum = 25;
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int random = r.nextInt(max);
            String result = Utils.getStringFromWeekOfTerm(random);
            System.out.println(result);
            System.out.println(Integer.toBinaryString(random));
            String[] strings = result.split(" ");
            String[] s2 = strings[0].split(",");
            int a = 0;
            for (String str : s2) {
                if (str == null || str.isEmpty())
                    continue;
                if (str.contains("-")) {
                    int space = 2;
                    if (strings[1].equals("[Tuần]")) {
                        space = 1;
                    }
                    String[] strs = str.split("-");
                    if (strs.length != 2) {
                        System.out.println("error");
                        return;
                    }
                    int p = Integer.valueOf(strs[0]);
                    int q = Integer.valueOf(strs[1]);

                    for (int n = p; n <= q; n += space) {
                        a += 1 << (maxWeekNum - n);
                    }
                } else {
                    a += 1 << (maxWeekNum - Integer.valueOf(str));
                }
            }
            assertEquals(random, a);
        }

    }
}