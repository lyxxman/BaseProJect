package com.ly.baseproject.utils;

import android.app.AlarmManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ַ����������߰�
 *
 * @author liux (http://my.oschina.net/liux)
 * @author thanatosx
 * @version 2.0
 *          Updated 2016-08-11
 */
public class StringUtils {

    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static Pattern IMG_URL = Pattern
            .compile(".*?(gif|jpeg|png|jpg|bmp)");

    private final static Pattern URL = Pattern
            .compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

    private final static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
    };

    private final static ThreadLocal<SimpleDateFormat> YYYYMMDD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
    };

    private final static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        }
    };

    private static final Pattern UniversalDatePattern = Pattern.compile(
            "([0-9]{4})-([0-9]{2})-([0-9]{2})[\\s]+([0-9]{2}):([0-9]{2}):([0-9]{2})"
    );

    /**
     * ���ַ���תλ��������
     *
     * @param sdate string date that's type like YYYY-MM-DD HH:mm:ss
     * @return {@link Date}
     */
    public static Date toDate(String sdate) {
        return toDate(sdate, YYYYMMDDHHMMSS.get());
    }

    public static Date toDate(String sdate, SimpleDateFormat formatter) {
        try {
            return formatter.parse(sdate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * YYYY-MM-DD HH:mm:ss��ʽ��ʱ���ַ���ת��Ϊ{@link Calendar}����
     *
     * @param str YYYY-MM-DD HH:mm:ss��ʽ�ַ���
     * @return {@link Calendar}
     */
    public static Calendar parseCalendar(String str) {
        Matcher matcher = UniversalDatePattern.matcher(str);
        Calendar calendar = Calendar.getInstance();
        if (!matcher.find()) return null;
        calendar.set(
                matcher.group(1) == null ? 0 : toInt(matcher.group(1)),
                matcher.group(2) == null ? 0 : toInt(matcher.group(2)) - 1,
                matcher.group(3) == null ? 0 : toInt(matcher.group(3)),
                matcher.group(4) == null ? 0 : toInt(matcher.group(4)),
                matcher.group(5) == null ? 0 : toInt(matcher.group(5)),
                matcher.group(6) == null ? 0 : toInt(matcher.group(6))
        );
        return calendar;
    }

    /**
     * transform date to string that's type like YYYY-MM-DD HH:mm:ss
     *
     * @param date {@link Date}
     * @return
     */
    public static String getDateString(Date date) {
        return YYYYMMDDHHMMSS.get().format(date);
    }

    /**
     * transform date to string that's type like YYYY-MM-DD HH:mm
     *
     * @param sdate
     * @return
     */
    public static String getDateString(String sdate) {
        if (TextUtils.isEmpty(sdate)) return "";
        return YYYYMMDDHHMM.get().format(toDate(sdate));
    }


    public static String formatYearMonthDay(String st) {
        Matcher matcher = UniversalDatePattern.matcher(st);
        if (!matcher.find()) return st;
        return String.format("%s��%s��%s��",
                matcher.group(1) == null ? 0 : matcher.group(1),
                matcher.group(2) == null ? 0 : matcher.group(2),
                matcher.group(3) == null ? 0 : matcher.group(3));
    }

    public static String formatYearMonthDayNew(String st) {
        Matcher matcher = UniversalDatePattern.matcher(st);
        if (!matcher.find()) return st;
        return String.format("%s/%s/%s",
                matcher.group(1) == null ? 0 : matcher.group(1),
                matcher.group(2) == null ? 0 : matcher.group(2),
                matcher.group(3) == null ? 0 : matcher.group(3));
    }

    /**
     * format time friendly
     *
     * @param sdate YYYY-MM-DD HH:mm:ss
     * @return n����ǰ, nСʱǰ, ����, ǰ��, n��ǰ, n����ǰ
     */
    public static String formatSomeAgo(String sdate) {
        if (sdate == null) return "";
        Calendar calendar = parseCalendar(sdate);
        if (calendar == null) return sdate;

        Calendar mCurrentDate = Calendar.getInstance();
        long crim = mCurrentDate.getTimeInMillis(); // current
        long trim = calendar.getTimeInMillis(); // target
        long diff = crim - trim;

        int year = mCurrentDate.get(Calendar.YEAR);
        int month = mCurrentDate.get(Calendar.MONTH);
        int day = mCurrentDate.get(Calendar.DATE);

        if (diff < 60 * 1000) {
            return "�ո�";
        }
        if (diff >= 60 * 1000 && diff < AlarmManager.INTERVAL_HOUR) {
            return String.format("%s����ǰ", diff / 60 / 1000);
        }
        mCurrentDate.set(year, month, day, 0, 0, 0);
        if (trim >= mCurrentDate.getTimeInMillis()) {
            return String.format("%sСʱǰ", diff / AlarmManager.INTERVAL_HOUR);
        }
        mCurrentDate.set(year, month, day - 1, 0, 0, 0);
        if (trim >= mCurrentDate.getTimeInMillis()) {
            return "����";
        }
        mCurrentDate.set(year, month, day - 2, 0, 0, 0);
        if (trim >= mCurrentDate.getTimeInMillis()) {
            return "ǰ��";
        }
        if (diff < AlarmManager.INTERVAL_DAY * 30) {
            return String.format("%s��ǰ", diff / AlarmManager.INTERVAL_DAY);
        }
        if (diff < AlarmManager.INTERVAL_DAY * 30 * 12) {
            return String.format("%s��ǰ", diff / (AlarmManager.INTERVAL_DAY * 30));
        }
        return String.format("%s��ǰ", mCurrentDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR));
    }

    /**
     * @param str YYYY-MM-DD HH:mm:ss string
     * @return ����, ����, ǰ��, n��ǰ
     */
    public static String formatSomeDay(String str) {
        return formatSomeDay(parseCalendar(str));
    }

    /**
     * @param calendar {@link Calendar}
     * @return ����, ����, ǰ��, n��ǰ
     */
    public static String formatSomeDay(Calendar calendar) {
        if (calendar == null) return "?��ǰ";
        Calendar mCurrentDate = Calendar.getInstance();
        long crim = mCurrentDate.getTimeInMillis(); // current
        long trim = calendar.getTimeInMillis(); // target
        long diff = crim - trim;

        int year = mCurrentDate.get(Calendar.YEAR);
        int month = mCurrentDate.get(Calendar.MONTH);
        int day = mCurrentDate.get(Calendar.DATE);

        mCurrentDate.set(year, month, day, 0, 0, 0);
        if (trim >= mCurrentDate.getTimeInMillis()) {
            return "����";
        }
        mCurrentDate.set(year, month, day - 1, 0, 0, 0);
        if (trim >= mCurrentDate.getTimeInMillis()) {
            return "����";
        }
        mCurrentDate.set(year, month, day - 2, 0, 0, 0);
        if (trim >= mCurrentDate.getTimeInMillis()) {
            return "ǰ��";
        }
        return String.format("%s��ǰ", diff / AlarmManager.INTERVAL_DAY);
    }

    /**
     * @param calendar {@link Calendar}
     * @return ����n
     */
    public static String formatWeek(Calendar calendar) {
        if (calendar == null) return "����?";
        return new String[]{"������", "����һ", "���ڶ�", "������", "������", "������", "������"}[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * @param str YYYY-MM-DD HH:mm:ss string
     * @return ����n
     */
    public static String formatWeek(String str) {
        return formatWeek(parseCalendar(str));
    }

    /**
     * @param sdate YYYY-MM-DD HH:mm:ss string
     * @return
     */
    public static String formatDayWeek(String sdate) {
        Calendar calendar = parseCalendar(sdate);
        if (calendar == null) return "??/?? ����?";
        Calendar mCurrentDate = Calendar.getInstance();
        String ws = formatWeek(calendar);
        int diff = mCurrentDate.get(Calendar.DATE) - calendar.get(Calendar.DATE);
        if (diff == 0) {
            return "���� / " + ws;
        }
        if (diff == 1) {
            return "���� / " + ws;
        }
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        return String.format("%s/%s / %s", formatInt(m), formatInt(d), ws);
    }

    /**
     * format to HH
     *
     * @param i integer
     * @return HH
     */
    public static String formatInt(int i) {
        return (i < 10 ? "0" : "") + i;
    }


    /**
     * ���ܸ�ʽ��
     */
    public static String friendly_time3(String sdate) {
        Calendar calendar = parseCalendar(sdate);
        if (calendar == null) return sdate;

        Calendar mCurrentDate = Calendar.getInstance();
        SimpleDateFormat formatter = YYYYMMDDHHMMSS.get();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String s = hour >= 0 && hour < 12 ? "����" : "����";
        s += " HH:mm";

        if (mCurrentDate.get(Calendar.DATE) == calendar.get(Calendar.DATE)) {
            formatter.applyPattern(s);
        } else if (mCurrentDate.get(Calendar.DATE) - calendar.get(Calendar.DATE) == 1) {
            formatter.applyPattern("���� " + s);
        } else if (mCurrentDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            formatter.applyPattern("MM-dd " + s);
        } else {
            formatter.applyPattern("YYYY-MM-dd " + s);
        }
        return formatter.format(calendar.getTime());
    }


    /**
     * �жϸ����ַ���ʱ���Ƿ�Ϊ����
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = YYYYMMDD.get().format(today);
            String timeDate = YYYYMMDD.get().format(time);
            if (nowDate.equals(timeDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * �Ƿ�����ͬ��һ��
     *
     * @param sdate1 sdate1
     * @param sdate2 sdate2
     * @return
     */
    public static boolean isSameDay(String sdate1, String sdate2) {
        if (TextUtils.isEmpty(sdate1) || TextUtils.isEmpty(sdate2)) {
            return false;
        }
        Date date1 = toDate(sdate1);
        Date date2 = toDate(sdate2);
        if (date1 != null && date2 != null) {
            String d1 = YYYYMMDD.get().format(date1);
            String d2 = YYYYMMDD.get().format(date2);
            if (d1.equals(d2)) {
                return true;
            }
        }
        return false;
    }

    public static String getCurrentTimeStr() {
        return YYYYMMDDHHMMSS.get().format(new Date());
    }

    /***
     * ��������ʱ�����ص��ǵ���s
     *
     * @param date1
     * @param date2
     * @return
     * @author ���� 2015-2-9 ����4:50:06
     */
    public static long calDateDifferent(String date1, String date2) {
        try {
            Date d1 = YYYYMMDDHHMMSS.get().parse(date1);
            Date d2 = YYYYMMDDHHMMSS.get().parse(date2);
            // ����ms
            long diff = d2.getTime() - d1.getTime();
            return diff / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * �жϸ����ַ����Ƿ�հ״��� �հ״���ָ�ɿո��Ʊ�����س��������з���ɵ��ַ��� �������ַ���Ϊnull����ַ���������true
     *
     * @param input
     * @return boolean
     */
    @Deprecated
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * �ж��ǲ���һ���Ϸ��ĵ����ʼ���ַ
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * �ж�һ��url�Ƿ�ΪͼƬurl
     *
     * @param url
     * @return
     */
    public static boolean isImgUrl(String url) {
        if (url == null || url.trim().length() == 0)
            return false;
        return IMG_URL.matcher(url).matches();
    }

    /**
     * �ж��Ƿ�Ϊһ���Ϸ���url��ַ
     *
     * @param str
     * @return
     */
    public static boolean isUrl(String str) {
        if (str == null || str.trim().length() == 0)
            return false;
        return URL.matcher(str).matches();
    }

    /**
     * �ַ���ת����
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            Log.d("oschina", e.getMessage());
        }
        return defValue;
    }

    /**
     * ����ת����
     *
     * @param obj
     * @return ת���쳣���� 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * ����ת����
     *
     * @param obj
     * @return ת���쳣���� 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * �ַ���ת����ֵ
     *
     * @param b
     * @return ת���쳣���� false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    public static String getString(String s) {
        return s == null ? "" : s;
    }

    /**
     * ��һ��InputStream��ת�����ַ���
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuilder res = new StringBuilder();
        BufferedReader read = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = read.readLine()) != null) {
                res.append(line).append("<br>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res.toString();
    }

    /***
     * ��ȡ�ַ���
     *
     * @param start �����￪ʼ��0����
     * @param num   ��ȡ���ٸ�
     * @param str   ��ȡ���ַ���
     * @return
     */
    public static String getSubString(int start, int num, String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (start < 0) {
            start = 0;
        }
        if (start > length) {
            start = length;
        }
        if (num < 0) {
            num = 1;
        }
        int end = start + num;
        if (end > length) {
            end = length;
        }
        return str.substring(start, end);
    }

    /**
     * ��ȡ��ǰʱ��Ϊÿ��ڼ���
     *
     * @return
     */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }

    /**
     * ��ȡ��ǰʱ��Ϊÿ��ڼ���
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
        week = week == 0 ? 52 : week;
        return week > 0 ? week : 1;
    }

    public static int[] getCurrentDate() {
        int[] dateBundle = new int[3];
        String[] temp = getDataTime("yyyy-MM-dd").split("-");

        for (int i = 0; i < 3; i++) {
            try {
                dateBundle[i] = Integer.parseInt(temp[i]);
            } catch (Exception e) {
                dateBundle[i] = 0;
            }
        }
        return dateBundle;
    }

    /**
     * ���ص�ǰϵͳʱ��
     */
    public static String getDataTime(String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date());
    }

}
