package com.sweetmanor.utils;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 使用 Apache Commons Lang 库的不进行测试
 */
class DateUtilTest {

    /**
     * 测试 setEndTime 方法是否能正确地将日期设置为当天的 23:59:59
     */
    @Test
    void testSetEndTime() {
        // 创建一个示例日期
        Date date = new Date();

        // 调用 setEndTime 方法
        Date endTime = DateUtil.setEndTime(date);

        // 验证小时是否设置为 23
        assertEquals(23, endTime.getHours());

        // 验证分钟是否设置为 59
        assertEquals(59, endTime.getMinutes());

        // 验证秒是否设置为 59
        assertEquals(59, endTime.getSeconds());
    }

    /**
     * 测试获取当月的最后一天
     */
    @Test
    void testGetLastDayOfMonth() {
        // 测试正常情况
        Date date = new Date();
        Date lastDay = DateUtil.getLastDayOfMonth(date);
        assertNotNull(lastDay);

        // 测试边界情况：1 月 31 日
        date = new Date(2023, 0, 10);
        lastDay = DateUtil.getLastDayOfMonth(date);
        assertEquals(31, lastDay.getDate());

        // 测试边界情况：2 月 28 日（非闰年）
        date = new Date(2023, 1, 10);
        lastDay = DateUtil.getLastDayOfMonth(date);
        assertEquals(28, lastDay.getDate());

        // 测试边界情况：2 月 29 日（闰年）
        date = new Date(2024, 1, 10);
        lastDay = DateUtil.getLastDayOfMonth(date);
        assertEquals(29, lastDay.getDate());
    }

    /**
     * 测试获取本周的第一天
     */
    @Test
    void testGetFirstDayOfWeek() {
        // 测试正常情况
        Date date = new Date();
        Date firstDayOfWeek = DateUtil.getFirstDayOfWeek(date);
        assertNotNull(firstDayOfWeek);

        // 测试边界情况：周日
        date = DateUtil.setDayOfWeek(new Date(), Calendar.SUNDAY);
        firstDayOfWeek = DateUtil.getFirstDayOfWeek(date);
        assertEquals(date, firstDayOfWeek);

        // 测试边界情况：周六
        date = DateUtil.setDayOfWeek(new Date(), Calendar.SATURDAY);
        firstDayOfWeek = DateUtil.getFirstDayOfWeek(date);
        // Calendar的星期从1开始，所以要加1
        assertEquals(Calendar.SUNDAY, firstDayOfWeek.getDay() + 1);
    }

    /**
     * 测试获取本周的最后一天
     */
    @Test
    void testGetLastDayOfWeek() {
        // 测试正常情况
        Date date = new Date();
        Date lastDayOfWeek = DateUtil.getLastDayOfWeek(date);
        assertNotNull(lastDayOfWeek);

        // 测试边界情况：周日
        date = DateUtil.setDayOfWeek(date, Calendar.SUNDAY);
        lastDayOfWeek = DateUtil.getLastDayOfWeek(date);
        // Calendar的星期从1开始，所以要加1
        assertEquals(Calendar.SATURDAY, lastDayOfWeek.getDay() + 1);

        // 测试边界情况：周六
        date = DateUtil.setDayOfWeek(date, Calendar.SATURDAY);
        lastDayOfWeek = DateUtil.getLastDayOfWeek(date);
        // Calendar的星期从1开始，所以要加1
        assertEquals(Calendar.SATURDAY, lastDayOfWeek.getDay() + 1);
    }

    /**
     * 测试 setDayOfWeek 方法是否能正确地将日期设置为指定的星期几
     */
    @Test
    void testSetDayOfWeek() {
        // 创建一个日期对象
        Date date = new Date();

        // 设置不同的星期几进行测试
        for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; dayOfWeek++) {
            // 调用方法设置星期几
            Date result = DateUtil.setDayOfWeek(date, dayOfWeek);

            // 获取设置后的日期的星期几
            Calendar cal = Calendar.getInstance();
            cal.setTime(result);
            int actualDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

            // 断言设置的星期几与预期一致
            assertEquals(dayOfWeek, actualDayOfWeek);
        }
    }

    /**
     * 测试 convertMillisToString 方法是否能正确地将毫秒数转换为时间字符串
     */
    @Test
    void testConvertMillisToString() {
        // 正常情况测试
        long millis = 3600000; // 1 小时
        String result = DateUtil.convertMillisToString(millis);
        assertEquals("1时 ", result);

        // 正常情况测试
        millis = 34218061001L;
        result = DateUtil.convertMillisToString(millis);
        assertEquals("1年 1月 1日 1时 1分 1秒 1毫秒", result);

        // 边界情况测试
        millis = 0;
        result = DateUtil.convertMillisToString(millis);
        assertEquals("", result);

        // 异常情况测试
        try {
            millis = -1;
            DateUtil.convertMillisToString(millis);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Milliseconds must be non-negative", e.getMessage());
        }
    }

}
