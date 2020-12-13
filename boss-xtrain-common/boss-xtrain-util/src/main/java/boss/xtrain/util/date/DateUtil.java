package boss.xtrain.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.*;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 日期操作工具类
 * @create 2020-7-2
 * @since 1.0
 */
public class DateUtil extends DateUtils {

    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String[] parsePatterns = {
            YYYY_MM_DD, YYYY_MM_DD_HH_MM_SS, "yyyy-MM-dd HH:mm", YYYY_MM,
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts) throws DateException {
        try{
            return new SimpleDateFormat(format).parse(ts);
        }catch (ParseException e){
            throw new DateException("DATE","200902","DATE_PARES_WRONG");
        }
    }

    /**
     * 计算两个时间差
     *
     * @param endDate 末时间 nowDate 头时间
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000L * 24 * 60 * 60;
        long nh = 1000L * 60 * 60;
        long nm = 1000L * 60;

        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 将日期型字符串转化为日期 格式
     */
    public static Date parseDate(String str) throws DateException {
        if (str == null) {
            return null;
        }
        try{
            return parseDate(str, parsePatterns);
        }catch (ParseException e){
            throw new DateException("DATE","200902","DATE_PARES_WRONG");
        }


    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }
}
