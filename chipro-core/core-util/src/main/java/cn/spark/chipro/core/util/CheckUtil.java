package cn.spark.chipro.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供一些对象有效性校验的方法
 */
@SuppressWarnings("rawtypes")
public final class CheckUtil {


    public static boolean isHaveNull(Object[] objArr) {
        boolean flag = true;
        if (objArr != null && objArr.length > 0) {
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] == null) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    public static boolean isStringHaveNull(String[] objArr) {
        boolean flag = true;
        if (objArr != null && objArr.length > 0) {
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] == null || "".equals(objArr[i])) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    public static boolean isNumber(String str) {
        if (str != null && !"".equals(str)) {
            //去掉前后空格
            str = str.trim();
            for (int i = 0; i < str.length(); i++) {
                // System.out.println(str.charAt(i));
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }

    }


    /***
     * 判断字符是否是中文
     *
     * @param c
     *            字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码 true 不乱，false 乱
     */
    public static boolean isMessyCode(String strName) {
        if (strName != null && !"".equals(strName)) {
            Pattern p = Pattern.compile("\\s*|t*|r*|n*");
            Matcher m = p.matcher(strName);
            String after = m.replaceAll("");
            String temp = after.replaceAll("\\p{P}", "");
            char[] ch = temp.trim().toCharArray();
            float chLength = ch.length;
            float count = 0;
            for (int i = 0; i < ch.length; i++) {
                char c = ch[i];
                if (!Character.isLetterOrDigit(c)) {
                    if (!isChinese(c)) {
                        count = count + 1;
                    }
                }
            }
            float result = count / chLength;
            return result > 0.4;
        } else {
            return true;
        }

    }

    public static boolean isUTF8NotMessyCode(String strName) {
        if (strName != null) {
            return !isMessyCode(strName);
        } else {
            return true;
        }


    }


    /**
     * 判断字符串是否是符合指定格式的时间
     *
     * @param date   时间字符串
     * @param format 时间格式
     * @return 是否符合
     */
    public final static boolean isDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断字符串有效性
     */
    public final static boolean valid(String src) {
        return !(src == null || "".equals(src.trim()));
    }

    /**
     * 判断一组字符串是否有效
     *
     * @param src
     * @return
     */
    public final static boolean valid(String... src) {
        for (String s : src) {
            if (!valid(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个对象是否为空
     */
    public final static boolean valid(Object obj) {
        return !(null == obj);
    }

    /**
     * 判断一组对象是否有效
     *
     * @param objs
     * @return
     */
    public final static boolean valid(Object... objs) {
        return objs != null && objs.length != 0;
    }

    /**
     * 判断集合的有效性
     */
    public final static boolean valid(Collection col) {
        return !(col == null || col.isEmpty());
    }

    /**
     * 判断一组集合是否有效
     *
     * @param cols
     * @return
     */
    public final static boolean valid(Collection... cols) {
        for (Collection c : cols) {
            if (!valid(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断map是否有效
     *
     * @param map
     * @return
     */
    public final static boolean valid(Map map) {
        return !(map == null || map.isEmpty());
    }

    /**
     * 判断一组map是否有效
     *
     * @param maps 需要判断map
     * @return 是否全部有效
     */
    public final static boolean valid(Map... maps) {
        for (Map m : maps) {
            if (!valid(m)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断对象为空
     *
     * @param obj 对象名
     * @return 是否为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof List)) {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String)) {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

    /**
     * 判断对象不为空
     *
     * @param obj 对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /*
     * 推荐，速度最快 判断是否为整数
     *
     * @param str 传入的字符串
     *
     * @return 是整数返回true,否则返回false
     */

    public static boolean isInteger(String str) {
        if (isNotEmpty(str)) {
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            return pattern.matcher(str).matches();
        } else {
            return false;
        }

    }

    // 方法1：用JAVA自带的函数
    public static boolean isNumeric(String str) {
        if (isNotEmpty(str)) {
            for (int i = str.length(); --i >= 0; ) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // 方法2：
    public static boolean isNumeric2(String str) {
        if (isNotEmpty(str)) {
            Pattern pattern = Pattern.compile("[0-9]*");
            return pattern.matcher(str).matches();
        } else {
            return false;
        }
    }

    // 方法3：
    public final static boolean isNumeric3(String str) {

        if (isNotEmpty(str) && !"".equals(str.trim())) {
            return str.matches("^[0-9]*$");
        } else {
            return false;
        }
    }

    // 方法4：用ascii码
    public static boolean isNumeric4(String str) {
        if (isNotEmpty(str)) {
            for (int i = str.length(); --i >= 0; ) {
                int chr = str.charAt(i);
                if (chr < 48 || chr > 57)
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }

}