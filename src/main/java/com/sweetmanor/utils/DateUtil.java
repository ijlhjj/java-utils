package com.sweetmanor.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 日期时间工具类：部分方法直接调用 commons-lang3 包的方法实现。 <br />
 * 调用 commons-lang3 实现的方法更多的是一个使用示例，具体使用时尽量直接用原方法，这样可以减少封装不好而引入的不必要错误。 <br />
 * 历史遗留代码，新代码应该使用 Java 8 新增的日期时间 API，此工具类将不再适用。
 *
 * @author ijlhjj
 * @version 1.0 2016-11-24
 */
public final class DateUtil {
    /**
     * 默认日期格式化模式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
    }

    /**
     * 获取当前日期的字符串表示，格式为默认格式（yyyy-MM-dd HH:mm:ss）
     *
     * @return 当前日期的字符串表示
     */
    public static String getDate() {
        // 使用 Apache Commons Lang 库的 DateFormatUtils 类来格式化日期
        return DateFormatUtils.format(new Date(), DEFAULT_FORMAT);
    }

    /**
     * 使用默认格式格式化日期
     *
     * @param date 要格式化的日期
     * @return 使用默认格式格式化后的日期字符串
     */
    public static String format(Date date) {
        // 使用 Apache Commons Lang 库的 DateFormatUtils 类来格式化日期
        return DateFormatUtils.format(date, DEFAULT_FORMAT);
    }

    /**
     * 使用指定格式格式化日期
     *
     * @param date    要格式化的日期
     * @param pattern 格式化模式字符串
     * @return 使用指定格式格式化后的日期字符串
     */
    public static String format(Date date, String pattern) {
        // 使用 Apache Commons Lang 库的 DateFormatUtils 类来格式化日期
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 对输入日期增加指定天数，返回新的Data对象，不改变原对象
     *
     * @param date   原日期对象, not null
     * @param amount 增加的天数，可以为负值，负值即减少指定天数
     * @return 计算后的日期对象
     */
    public static Date addDays(Date date, int amount) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来增加日期的天数
        return DateUtils.addDays(date, amount);
    }

    /**
     * 对输入日期增加指定月数，返回新的Data对象，不改变原对象
     *
     * @param date   原日期对象, not null
     * @param amount 增加的月数，可以为负值，负值即减少指定月数
     * @return 计算后的日期对象
     */
    public static Date addMonths(Date date, int amount) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来增加日期的月数
        return DateUtils.addMonths(date, amount);
    }

    /**
     * 对输入日期增加指定星期数，返回新的Data对象，不改变原对象
     *
     * @param date   原日期对象, not null
     * @param amount 增加的星期数，可以为负值，负值即减少指定星期数
     * @return 计算后的日期对象
     */
    public static Date addWeeks(Date date, int amount) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来增加日期的星期数
        return DateUtils.addWeeks(date, amount);
    }

    /**
     * 设置日期的天数为指定值，不改变时间。例如要获取当月第一天，amount为1
     *
     * @param date   原日期对象, not null
     * @param amount 要设定的天
     * @return 计算后的日期对象
     */
    public static Date setDays(Date date, int amount) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来设置日期的天数
        return DateUtils.setDays(date, amount);
    }

    /**
     * 设置日期的小时为指定值，其他字段不变
     *
     * @param date   原日期对象, not null
     * @param amount 要设定的小时
     * @return 计算后的日期对象
     */
    public static Date setHours(Date date, int amount) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来设置日期的小时数
        return DateUtils.setHours(date, amount);
    }

    /**
     * 设置日期的分钟为指定值，其他字段不变
     *
     * @param date   原日期对象, not null
     * @param amount 要设定的分钟
     * @return 计算后的日期对象
     */
    public static Date setMinutes(Date date, int amount) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来设置日期的分钟数
        return DateUtils.setMinutes(date, amount);
    }

    /**
     * 设置日期的秒为指定值，其他字段不变
     *
     * @param date   原日期对象, not null
     * @param amount 要设定的秒
     * @return 计算后的日期对象
     */
    public static Date setSeconds(Date date, int amount) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来设置日期的秒数
        return DateUtils.setSeconds(date, amount);
    }

    /**
     * 设置为指定日期的 00:00:00
     *
     * @param date 原日期对象, not null
     * @return 计算后的日期对象
     */
    public static Date setBeginTime(Date date) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来截取日期到指定字段（天）
        return truncate(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 设置为指定日期的 23:59:59
     *
     * @param date 原日期对象, not null
     * @return 计算后的日期对象
     */
    public static Date setEndTime(Date date) {
        // 先设置日期的小时为 23
        Date d = setHours(date, 23);
        // 然后设置日期的分钟为 59
        d = setMinutes(d, 59);
        // 最后设置日期的秒为 59
        d = setSeconds(d, 59);
        // 返回最终设置好的日期对象
        return d;
    }

    /**
     * 获取指定日期所在月份的最后一天，不改变时间
     *
     * @param date 原日期对象, not null
     * @return 计算后的日期对象
     */
    public static Date getLastDayOfMonth(Date date) {
        // 设置日期为当月的第一天
        Date d = setDays(date, 1);
        // 在第一天的基础上增加一个月
        d = addMonths(d, 1);
        // 在增加一个月后的日期上减去一天，即得到当月的最后一天
        return addDays(d, -1);
    }

    /**
     * 获取指定日期所在周的第一天（周日），不改变时间
     *
     * @param date 原日期对象, not null
     * @return 计算后的日期对象
     */
    public static Date getFirstDayOfWeek(Date date) {
        // 使用 setDayOfWeek 方法将日期设置为所在周的周日
        return setDayOfWeek(date, Calendar.SUNDAY);
    }

    /**
     * 获取指定日期所在周的最后一天（周六），不改变时间
     *
     * @param date 原日期对象, not null
     * @return 计算后的日期对象
     */
    public static Date getLastDayOfWeek(Date date) {
        // 使用 setDayOfWeek 方法将日期设置为所在周的周六
        return setDayOfWeek(date, Calendar.SATURDAY);
    }

    /**
     * 设置为指定的星期几，不改变时间
     *
     * @param dayOfWeek 设置为指定星期几，可接受以下参数： Calendar.MONDAY - Calendar.SUNDAY
     * @return 计算后的日期对象
     */
    public static Date setDayOfWeek(Date date, int dayOfWeek) {
        // 将日期转换为日历对象
        Calendar cal = DateUtils.toCalendar(date);
        // 设置日历的星期几为指定的星期几
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        // 将日历对象转换回日期对象并返回
        return cal.getTime();
    }

    /**
     * 对日期按指定字段进行截取
     *
     * @param date  要截取的日期
     * @param field 截取字段：使用Calendar的字段值。
     *              例如要获取当天的00:00:00，field为Calendar.DAY_OF_MONTH
     * @return 计算后的日期对象
     */
    public static Date truncate(Date date, int field) {
        // 使用 Apache Commons Lang 库的 DateUtils 类来截取日期到指定字段
        return DateUtils.truncate(date, field);
    }

    /**
     * 将毫秒转换为中文表示的时间字符串，月按 30 天计算
     *
     * @param millis 要转换的毫秒数
     * @return 转换后的中文时间字符串
     * @throws IllegalArgumentException 如果输入的毫秒值无效
     */
    public static String convertMillisToString(long millis) {
        // 如果毫秒数小于 0，则抛出 IllegalArgumentException 异常
        if (millis < 0) {
            throw new IllegalArgumentException("Milliseconds must be non-negative");
        }

        // 计算年数
        long years = TimeUnit.MILLISECONDS.toDays(millis) / 365;
        // 计算月数
        long months = TimeUnit.MILLISECONDS.toDays(millis) % 365 / 30;
        // 计算天数
        long days = TimeUnit.MILLISECONDS.toDays(millis) % 365 % 30;
        // 计算小时数
        long hours = TimeUnit.MILLISECONDS.toHours(millis) % 24;
        // 计算分钟数
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        // 计算秒数
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        // 计算毫秒数
        long milliseconds = millis % 1000;

        // 创建一个 StringBuilder 对象来存储结果字符串
        StringBuilder result = new StringBuilder();

        // 如果年数大于 0，则将年数添加到结果字符串中
        if (years > 0) {
            result.append(years).append("年 ");
        }
        // 如果月数大于 0，则将月数添加到结果字符串中
        if (months > 0) {
            result.append(months).append("月 ");
        }
        // 如果天数大于 0，则将天数添加到结果字符串中
        if (days > 0) {
            result.append(days).append("日 ");
        }
        // 如果小时数大于 0，则将小时数添加到结果字符串中
        if (hours > 0) {
            result.append(hours).append("时 ");
        }
        // 如果分钟数大于 0，则将分钟数添加到结果字符串中
        if (minutes > 0) {
            result.append(minutes).append("分 ");
        }
        // 如果秒数大于 0，则将秒数添加到结果字符串中
        if (seconds > 0) {
            result.append(seconds).append("秒 ");
        }
        // 如果毫秒数大于 0，则将毫秒数添加到结果字符串中
        if (milliseconds > 0) {
            result.append(milliseconds).append("毫秒");
        }

        // 返回最终的结果字符串
        return result.toString();
    }

}
