package com.example.demo.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * 时间工具类
 *
 * @author wujian
 * @date 2016-09-01 16:10
 * @company 上海若美科技有限公司
 */
// TODO: 2020/3/30  modify 过时方法 ，add 显示即时消息方法
public class DateUtil {
    private static String datePattern = "yyyy-MM-dd_HH-mm-ss";

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 格式化日期到时分秒 yyyy-MM-dd_HH-mm-ss
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(datePattern);
        String result = null;
        try {
            result = dateTimeFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    public static long str2LongTime(String dateStr) {
        Date date = str2Date(dateStr, FORMAT);
        return date == null ? 0 : date.getTime();

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String date2Str(String str, String format) {// yyyy-MM-dd HH:mm:ss
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        Date date;
        try {
            date = sdf.parse(str);
            if (format == null || format.length() == 0) {
                format = FORMAT;
            }

            SimpleDateFormat sdf2 = new SimpleDateFormat(format);
            String s = sdf2.format(date);
            return s;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String date2Str(String str, String format1, String format2) {// yyyy-MM-dd HH:mm:ss
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format1);
        Date date;
        try {
            date = sdf.parse(str);
            if (format2 == null || format2.length() == 0) {
                format2 = FORMAT;
            }

            SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
            String s = sdf2.format(date);
            return s;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + "-"
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
                + ":" + c.get(Calendar.SECOND);
    }

    public static String getCurYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "";
    }

    public static String getCurMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return (c.get(Calendar.MONTH) + 1) + "";
    }

    public static String getCurDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.DAY_OF_MONTH) + "";
    }


    /**
     * 获得当前日期的字符串格式
     *
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    // 格式到秒
    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    /**
     * 格式到天
     *
     * @param time
     * @return
     */
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy.MM.dd").format(time);

    }

    /**
     * @param time
     * @return
     * @方法描述: 格式化得到 小时：分钟
     */
    public static String getMinute(long time) {
        return new SimpleDateFormat("HH:mm").format(time);
    }

    /**
     * 获取秒
     *
     * @param time
     * @return
     */
    public static String getSecond(long time) {
        return new SimpleDateFormat("HH:mm:ss").format(time);
    }


    // 格式到毫秒
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }

    /**
     * 字符串时间转换为固定的格式
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;

    }


    /**
     * 格式化当前时间
     *
     * @return
     */
    public static String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * @param context
     * @param editText
     * @return
     * @方法描述: 日期选择器
     */
    public static Dialog createDataDialog(Context context, final EditText editText) {
        // 用来获取日期和时间的
        Calendar calendar = Calendar.getInstance();
        Dialog dialog = null;
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month,
                                  int dayOfMonth) {
                // Calendar月份是从0开始,所以month要加1
                editText.setText(DateUtil.getDay(
                        DateUtil.str2Date(
                                year + "-" + (month + 1) + "-" + dayOfMonth
                                        + " 00:00:00").getTime()).replace(".",
                        "-"));
            }
        };
        dialog = new DatePickerDialog(context, dateListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        return dialog;
    }


    /**
     * 计算两个时间之间相隔的天数
     *
     * @param milliTime1
     * @param milliTime2
     * @return
     */
    public static long caculateDifferDays(long milliTime1, long milliTime2) {
        long differDays = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(milliTime1);
        Date date2 = new Date(milliTime2);
        try {
            date1 = format.parse(format.format(date1));
            date2 = format.parse(format.format(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        differDays = (date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24);
        return Math.abs(differDays);
    }

    /**
     * @param context
     * @param editText
     * @return
     * @方法描述: 时间选择器
     */
    public static Dialog createTimerDialog(Context context, final EditText editText) {
        // 用来获取日期和时间的
        Calendar calendar = Calendar.getInstance();
        Dialog dialog = null;
        TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timerPicker, int hourOfDay,
                                  int minute) {
                editText.setText(hourOfDay + ":" + minute);
            }
        };
        dialog = new TimePickerDialog(context, timeListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false); // 是否为二十四制
        return dialog;
    }

    public static String getMessageShowDateStr(String dateString) {
//		private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(FORMAT);
        try {
            Date date = format.parse(dateString);
            long differDays = caculateDifferDays(date.getTime(), new Date().getTime());
            if (differDays == 0) {
                return getMinute(date.getTime());
            } else if (differDays == 1) {
                return "昨天 " + getMinute(date.getTime());
            } else if (differDays == 2) {
                return "前天 " + getMinute(date.getTime());
            } else if (differDays < 365) {
                return date2Str(date, "MM-dd HH:mm");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static String getWaeahterTodayTimeStrByPosition(int Position) {
        switch (Position) {
            case 0:
                return "今天";
            case 1:
                return "明天";
            default:
                break;
        }
        return "";
    }

    /**
     * 毫秒转换成大致时间
     *
     * @param milliseconds
     * @return
     */
    // TODO: 2020/3/30  modify  更新过时方法
    public static String getTimeFromStr(long milliseconds) {
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        Calendar inputDate = Calendar.getInstance();
        inputDate.setTime(new Date(milliseconds));

        String time = null;//MMddhh
        long tempMilliseconds = milliseconds;
//		Log.e("tempMilliseconds",new Date(tempMilliseconds).toString()+"");
        milliseconds = (new Date().getTime() - milliseconds) / 1000;
        if (milliseconds < 60) {
            time = "刚刚";
        } else if (milliseconds < 60 * 60) {
            time = milliseconds / 60 + "分钟前";
        } else if (inputDate.get(Calendar.DAY_OF_MONTH) == nowDate.get(Calendar.DAY_OF_MONTH)) {
//			 time = milliseconds/60/60+"小时前";
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            time = "今天 " + format.format(new Date(tempMilliseconds));
        } else if (inputDate.get(Calendar.YEAR) == nowDate.get(Calendar.YEAR)) {
//			 time = milliseconds/60/60/24+"天前";
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
            time = format.format(new Date(tempMilliseconds));
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            time = format.format(new Date(tempMilliseconds));
        }
        return time;
    }


    public static void getHourMinutesSecondesMap(long time, Map<String, String> map) {
        if (time <= 0) {
            map.put("hour", "00");
            map.put("minutes", "00");
            map.put("secondes", "00");
            return;
        }
        if (time / (1000 * 60 * 60) > 0) {
            int hour = (int) time / (1000 * 60 * 60);
            String str = "";
            if (hour < 10) {
                str = "0" + hour;
            } else {
                str = "" + hour;
            }
            map.put("hour", str);
            time %= (1000 * 60 * 60);
        } else {
            map.put("hour", "00");
        }
        if (time / (1000 * 60) > 0) {
            int minutes = (int) time / (1000 * 60);
            String str = "";
            if (minutes < 10) {
                str = "0" + minutes;
            } else {
                str = "" + minutes;
            }
            map.put("minutes", str);
            time %= (1000 * 60);
        } else {
            map.put("minutes", "00");
        }
        if (time / 1000 > 0) {
            int secondes = (int) (time / 1000);
            String str = "";
            if (secondes < 10) {
                str = "0" + secondes;
            } else {
                str = "" + secondes;
            }
            map.put("secondes", str);
        } else {
            map.put("secondes", "00");
        }

    }

    public static String formatTime2String(long time, String pattern) {
        String dateStr = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            dateStr = sdf.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }


    /**
     * 格式化聊天时间
     * 聊天时间显示 ： 2天以内显示规则：1.十分钟以内不显示时间，十分钟以上 显示， 当天显示格式： 时:分   昨天显示格式： 昨天 时:分 ；
     * 2天以上显示 月 日 时 分
     * 不是同一年份的显示： 年 月 日 时:分
     *
     * @param inputTime 时间戳
     * @return
     */
    public static String format2MsgTime(long inputTime) {

        Date inputDate = new Date(inputTime);
        Calendar inputCalendar = Calendar.getInstance();
        inputCalendar.setTime(inputDate);

        Date nowDate = new Date();
        long nowTime = nowDate.getTime();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(nowDate);

        int yearOfInput = inputCalendar.get(Calendar.YEAR);
        int monthOfInput = inputCalendar.get(Calendar.MONTH);
        int dayOfInput = inputCalendar.get(Calendar.DAY_OF_MONTH);

        int yearOfNow = nowCalendar.get(Calendar.YEAR);
        int monthOfNow = nowCalendar.get(Calendar.MONTH);
        int dayOfNow = nowCalendar.get(Calendar.DAY_OF_MONTH);

        if (yearOfInput != yearOfNow) {
            return formatTime2String(inputTime, "yyyy年MM月dd日 HH:mm");
        } else {
            if (monthOfInput == monthOfNow) {
                if (dayOfInput == dayOfNow) {
                    //判断消息时间是否在十分钟之内
                    if (nowTime - inputTime <= 60 * 1000 * 10) {
                        return "";
                    } else {
                        return formatTime2String(inputTime, "HH:mm");
                    }
                } else if (dayOfInput == dayOfNow - 1) {
                    return "昨天 " + formatTime2String(inputTime, "HH:mm");
                } else {
                    String timeStr = formatTime2String(inputTime, "MM月dd日 HH:mm");
                    if(timeStr.startsWith("0")){
                        timeStr = timeStr.substring(1,timeStr.length()-1);
                    }
                    return timeStr;
                }

            } else {
                return formatTime2String(inputTime, "yyyy年MM月dd日 HH:mm");
            }
        }
    }


    /**
     *  * 返回指定pattern样的日期时间字符串。
     *  * @param pattern
     *  * @return 如果时间转换成功则返回结果，否则返回空字符串""
     * <p>
     *  
     */

    public static String getTimeString(Date date, String pattern) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            result = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 仿照微信中的消息时间显示逻辑，将时间戳（单位：毫秒）转换为友好的显示格式.
     *
     * <p>
     * <p>
     * 1）两天之内的日期显示逻辑是：今天、昨天(-1d)、前天(-2d)、星期？（只显示总计7天之内的星期数，即<=-4d）；<br>
     * <p>
     * 2）7天之外（即>7天）的逻辑：直接显示完整日期时间。
     *
     * @param srcDate         要处理的源日期时间对象
     * @param mustIncludeTime true表示输出的格式里一定会包含“时间:分钟”，否则不包含（参考微信，不包含时分的情况，用于首页“消息”中显示时）
     * @return 输出格式形如：“10:30”、“昨天 12:04”、“前天 20:51”、“星期二”、“2019/2/21 12:09”等形式
     * @since 4.5
     */

    public static String getTimeStringAutoShort2(Date srcDate, boolean mustIncludeTime) {

        String result = "";

        GregorianCalendar gcCurrent = new GregorianCalendar();
        gcCurrent.setTime(new Date());

        int currentYear = gcCurrent.get(GregorianCalendar.YEAR);

        int currentMonth = gcCurrent.get(GregorianCalendar.MONTH) + 1;

        int currentDay = gcCurrent.get(GregorianCalendar.DAY_OF_MONTH);
        GregorianCalendar gcSrc = new GregorianCalendar();
        gcSrc.setTime(srcDate);
        int srcYear = gcSrc.get(GregorianCalendar.YEAR);
        int srcMonth = gcSrc.get(GregorianCalendar.MONTH) + 1;
        int srcDay = gcSrc.get(GregorianCalendar.DAY_OF_MONTH);
// 要额外显示的时间分钟
        String timeExtraStr = (mustIncludeTime ? " " + getTimeString(srcDate, "HH:mm") : "");
// 当年
        if (currentYear == srcYear) {
            long currentTimestamp = gcCurrent.getTimeInMillis();
            long srcTimestamp = gcSrc.getTimeInMillis();
// 相差时间（单位：毫秒）
            long delta = (currentTimestamp - srcTimestamp);


// 当天（月份和日期一致才是）

            if (currentMonth == srcMonth && currentDay == srcDay) {

// 时间相差60秒以内

                if (delta < 60 * 1000)
                    result = "刚刚";
// 否则当天其它时间段的，直接显示“时:分”的形式
                else
                    result = getTimeString(srcDate, "HH:mm");
            } else {//当年 && 当天之外的时间（即昨天及以前的时间）

// 昨天（以“现在”的时候为基准-1天）
                GregorianCalendar yesterdayDate = new GregorianCalendar();
                yesterdayDate.add(GregorianCalendar.DAY_OF_MONTH, -1);
// 前天（以“现在”的时候为基准-2天）
                GregorianCalendar beforeYesterdayDate = new GregorianCalendar();
                beforeYesterdayDate.add(GregorianCalendar.DAY_OF_MONTH, -2);
// 用目标日期的“月”和“天”跟上方计算出来的“昨天”进行比较，是最为准确的（如果用时间戳差值
// 的形式，是不准确的，比如：现在时刻是2019年02月22日1:00、而srcDate是2019年02月21日23:00，
// 这两者间只相差2小时，直接用“delta/(3600 * 1000)” > 24小时来判断是否昨天，就完全是扯蛋的逻辑了）
                if (srcMonth == (yesterdayDate.get(GregorianCalendar.MONTH) + 1)
                        && srcDay == yesterdayDate.get(GregorianCalendar.DAY_OF_MONTH)) {
                    result = "昨天" + timeExtraStr;// -1d
                }
// “前天”判断逻辑同上
                else if (srcMonth == (beforeYesterdayDate.get(GregorianCalendar.MONTH) + 1)
                        && srcDay == beforeYesterdayDate.get(GregorianCalendar.DAY_OF_MONTH)) {
                    result = "前天" + timeExtraStr;// -2d
                } else {
// 跟当前时间相差的小时数
                    long deltaHour = (delta / (3600 * 1000));
// 如果小于 7*24小时就显示星期几
                    if (deltaHour < 7 * 24) {
                        String[] weekday = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
// 取出当前是星期几
                        String weedayDesc = weekday[gcSrc.get(GregorianCalendar.DAY_OF_WEEK) - 1];
                        result = weedayDesc + timeExtraStr;
                    }
// 否则直接显示完整日期时间
                    else
                        result = getTimeString(srcDate, "yyyy/M/d") + timeExtraStr;
                }
            }
        } else
            result = getTimeString(srcDate, "yyyy/M/d") + timeExtraStr;
        return result;
    }
}