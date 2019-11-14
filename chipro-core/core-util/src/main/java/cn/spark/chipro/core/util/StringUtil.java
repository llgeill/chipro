package cn.spark.chipro.core.util;


import cn.spark.chipro.core.impl.BCConvert;
import cn.spark.chipro.core.impl.StringImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 提供些常用的字符串相关的工具方法
 */
public final class StringUtil {


    private static final int INDEX_NOT_FOUND = -1;
    private static final String EMPTY = "";
    /**
     * <p>
     * The maximum size to which the padding constant(s) can expand.
     * </p>
     */
    private static final int PAD_LIMIT = 8192;

    public static void main(String[] args) {
        String a = null;
        String b = "q";
        String c = "";
        System.out.println(isAllEmptyStrs(a, b, c));
        System.out.println(isNumber("7701"));
        System.out.println(isBigDecimal("-1000.20556"));
    }

    public static boolean strToBoolean(String str, boolean defaultVal) {
        if (!isEmptyTrim(str)) {
            if ("true".equals(str.trim().toLowerCase())) {
                defaultVal = true;
            } else if ("false".equals(str.trim().toLowerCase())) {
                defaultVal = false;
            }

        }
        return defaultVal;
    }

    public static Boolean isBoolean(String str) {
        Boolean flag = null;
        if (!isEmptyTrim(str)) {
            if ("true".equals(str.trim().toLowerCase())) {
                flag = true;
            } else if ("false".equals(str.trim().toLowerCase())) {
                flag = false;
            }

        }

        return flag;
    }

    public static Integer strToInteger(String str) {
        if (isNumber(str)) {
            return Integer.valueOf(str);
        }
        return null;
    }

    public static boolean isNumber(String str) {
        if (!isEmptyTrim(str)) {
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

    public static boolean isBigDecimal(String str) {

        if (!isEmptyTrim(str)) {
            //去掉前后空格
            str = str.trim();
            String reg = "([0-9]|-[0-9])(\\.|[0-9]*)";
            return Pattern.compile(reg).matcher(str).find();
        } else {
            return false;
        }

    }

    /**
     * 是否是正整数
     *
     * @param str
     * @return
     */
    public static boolean isPositiveInteger(String str) {
        if (!isEmptyTrim(str)) {
            //去掉前后空格
            str = str.trim();
            String reg = "^\\d+$";
            return Pattern.compile(reg).matcher(str).find();
        } else {
            return false;
        }

    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @param str 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isEmptyTrim(String str) {
        boolean flag = false;
        if (str == null || "".equals(str)) {
            flag = true;
        } else {
            str = str.trim();
            if (str == null || "".equals(str)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 判断是否有空字符串 null和"" 都返回 true
     *
     * @param strs 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmptyStrs(String... strs) {
        boolean flag = false;
        for (String string : strs) {
            flag = flag || string == null || "".equals(string);
        }
        return flag;
    }

    /**
     * 判断是否 都是空字符串 null和"" 都返回 true
     *
     * @param strs
     * @return
     */
    public static boolean isAllEmptyStrs(String... strs) {
        boolean flag = true;
        for (String string : strs) {
            flag = flag && (string == null || "".equals(string));
        }
        return flag;
    }

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @param strArr 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmptyStrArr(String[] strArr) {
        boolean flag = false;
        for (String string : strArr) {
            flag = flag || string == null || "".equals(string);
        }
        return flag;
    }

    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param list   需要处理的列表
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String joinString(List<?> list, String symbol) {
        String result = "";
        if (list != null) {
            for (Object o : list) {
                String temp = o.toString();
                if (temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 判定第一个字符串是否等于的第二个字符串中的某一个值
     *
     * @param str1 测试的字符串
     * @param str2 字符串数组(用,分割)
     * @return 是否包含
     */
    public static boolean requals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(",");
            for (String t : arr) {
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判定第一个字符串是否等于的第二个字符串中的某一个值
     *
     * @param str1  测试的字符串
     * @param str2  字符串数组
     * @param split str2字符串的分隔符
     * @return 是否包含
     */
    public static boolean requals(String str1, String str2, String split) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(split);
            for (String t : arr) {
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串省略截取
     * 字符串截取到指定长度size+...的形式
     *
     * @param subject 需要处理的字符串
     * @param size    截取的长度
     * @return 处理后的字符串
     */
    public static String subStringOmit(String subject, int size) {
        if (subject != null && subject.length() > size) {
            subject = subject.substring(0, size) + "...";
        }
        return subject;
    }

    /**
     * 截取字符串　超出的字符用symbol代替
     *
     * @param str    需要处理的字符串
     * @param len    字符串长度
     * @param symbol 最后拼接的字符串
     * @return 测试后的字符串
     */
    public static String subStringSymbol(String str, int len, String symbol) {
        String temp = "";
        if (str != null && str.length() > len) {
            temp = str.substring(0, len) + symbol;
        }
        return temp;
    }

    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param array  需要处理的字符串数组
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String joinString(String[] array, String symbol) {
        String result = "";
        if (array != null) {
            for (String temp : array) {
                if (temp != null && temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1 && CheckUtil.valid(symbol)) {
                result = result.substring(0, result.length() - symbol.length());
            }
        }
        return result;
    }

    /**
     * 将一组字符才以指定的字符链接起来
     *
     * @param linkStr 链接字符
     * @param strs    需要连接的动态参数
     * @return
     */
    public static String join(String linkStr, String... strs) {
        StringBuffer result = new StringBuffer();
        for (String temp : strs) {
            if (temp != null && temp.trim().length() > 0)
                result.append(temp + linkStr);
        }
        if (result.length() > 1 && CheckUtil.valid(linkStr)) {
            return result.substring(0, result.length() - linkStr.length());
        }
        return result.toString();
    }

    /**
     * 隐藏邮件地址前缀。
     *
     * @param email - EMail邮箱地址 例如: ssss@koubei.com 等等...
     * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
     */
    public static String getHideEmailPrefix(String email) {
        if (null != email) {
            int index = email.lastIndexOf('@');
            if (index > 0) {
                email = repeat("*", index).concat(email.substring(index));
            }
        }
        return email;
    }

    /**
     * repeat - 通过源字符串重复生成N次组成新的字符串。
     *
     * @param src - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
     * @param num - 重复生成次数
     * @return 返回已生成的重复字符串
     */
    public static String repeat(String src, int num) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < num; i++)
            s.append(src);
        return s.toString();
    }

    /**
     * 截取字符串左侧的Num位截取到末尾
     *
     * @param str1 处理的字符串
     * @param num  开始位置
     * @return 截取后的字符串
     */
    public static String ltrim(String str1, int num) {
        String tt = "";
        if (!isEmpty(str1) && str1.length() >= num) {
            tt = str1.substring(num, str1.length());
        }
        return tt;

    }

    /**
     * 截取字符串右侧第0位到第Num位
     *
     * @param str 处理的字符串
     * @param num 截取的位置
     * @return 截取后的字符串
     */
    public static String rtrim(String str, int num) {
        if (!isEmpty(str) && str.length() > num) {
            str = str.substring(0, str.length() - num);
        }
        return str;
    }

    /**
     * 根据指定的字符把源字符串分割成一个list
     *
     * @param src     处理的字符串
     * @param pattern 分割字符串
     * @return 处理后的list
     */
    public static List<String> parseString2List(String src, String pattern) {
        List<String> list = new ArrayList<>();
        if (src != null) {
            String[] tt = src.split(pattern);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }

    /**
     * 格式化一个float
     *
     * @param format 要格式化成的格式 such as #.00, #.#
     * @return 格式化后的字符串
     */
    public static String formatDouble(double f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }

    public static String formatStr(String format, Object... arguments) {
        String str = format;
        for (Object object : arguments) {
            if (object != null) {
                //regex -- 匹配此字符串的正则表达式。
                str = str.replaceFirst("\\{\\}", object.toString());
            } else {
                str = str.replaceFirst("\\{\\}", "");
            }
        }
        return str;
    }

    /**
     * 截取字符串左侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     */
    public static String left(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(0, count);
    }

    /**
     * 截取字符串右侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     * Summary 其他编码的有待测试
     */
    public static String right(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(input.length() - count, input.length());
    }

    /**
     * 全角字符变半角字符
     *
     * @param str 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String full2Half(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return BCConvert.qj2bj(str);
    }

    /**
     * 半角字符变全角字符
     *
     * @param str 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String Half2Full(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return BCConvert.bj2qj(str);
    }

    /**
     * 页面中去除字符串中的空格、回车、换行符、制表符
     *
     * @param str 需要处理的字符串
     */
    public static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }

    /**
     * 判断字符串数组中是否包含某字符串元素
     *
     * @param substring 某字符串
     * @param source    源字符串数组
     * @return 包含则返回true，否则返回false
     */
    public static boolean isIn(String substring, String[] source) {
        if (isEmpty(substring) || !CheckUtil.valid(source)) {
            return false;
        }
        for (String t : source) {
            if (substring.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串转换unicode.实现native2ascii.exe类似的功能
     *
     * @param string 需要处理的字符串
     */
    public static String string2Unicode(String string) {
        StringBuilder uni = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            String temp = "\\u" + String.valueOf(Integer.toHexString(string.charAt(i)));
            uni.append(temp);
        }
        return uni.toString();
    }

    /**
     * 转字符串 实现native2ascii.exe类似的功能
     *
     * @param unicode 需要处理的字符串
     */
    public static String unicode2String(String unicode) {
        StringBuilder str = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            str.append((char) data);
        }
        return str.toString();
    }

    /**
     * 删除所有的标点符号
     *
     * @param str 处理的字符串
     */
    public static String trimPunct(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.replaceAll("[\\pP\\p{Punct}]", "");
    }

    /**
     * 字符串相似度比较(速度较快)
     */
    public static double SimilarityRatio(String str1, String str2) {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length()) {
            return StringImpl.SimilarityRatio(str1, str2);
        } else {
            return StringImpl.SimilarityRatio(str2, str1);
        }

    }

    /**
     * 字符串相似度比较(速度较快)
     */
    public static double SimilarDegree(String str1, String str2) {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length()) {
            return StringImpl.SimilarDegree(str1, str2);
        } else {
            return StringImpl.SimilarDegree(str2, str1);
        }
    }

    /**
     * 获取字符串str在String中出现的次数
     *
     * @param string 处理的字符串
     * @param str    子字符串
     */
    public static int countSubStr(String string, String str) {
        if ((str == null) || (str.length() == 0) || (string == null) || (string.length() == 0)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1) {
            count++;
            index += str.length();
        }
        return count;
    }

    /**
     * 替换一个出现的子串
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public static String replaceFirst(String s, String sub, String with) {
        int i = s.indexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }

    /**
     * 替换最后一次出现的字串
     * Replaces the very last occurrence of a substring with supplied string.
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public static String replaceLast(String s, String sub, String with) {
        int i = s.lastIndexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }

    /**
     * 删除所有的子串
     * Removes all substring occurrences from the string.
     *
     * @param s   source string
     * @param sub substring to remove
     */
    public static String remove(String s, String sub) {
        int c = 0;
        int sublen = sub.length();
        if (sublen == 0) {
            return s;
        }
        int i = s.indexOf(sub, c);
        if (i == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        do {
            sb.append(s, c, i);
            c = i + sublen;
        } while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length()) {
            sb.append(s, c, s.length());
        }
        return sb.toString();
    }

    /**
     * 将字符串首字母转大写
     *
     * @param str 需要处理的字符串
     */
    public static String upperFirstChar(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] cs = str.toCharArray();
        if ((cs[0] >= 'a') && (cs[0] <= 'z')) {
            cs[0] -= (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * 将字符串首字母转小写
     *
     * @param str
     * @return
     */
    public static String lowerFirstChar(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] cs = str.toCharArray();
        if ((cs[0] >= 'A') && (cs[0] <= 'Z')) {
            cs[0] += (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * 判读俩个字符串右侧的length个字符串是否一样
     *
     * @param str1
     * @param str2
     * @param length
     * @return
     */
    public static boolean rigthEquals(String str1, String str2, int length) {
        return right(str1, length).equals(right(str2, length));
    }

    /**
     * 判读俩个字符串左侧的length个字符串是否一样
     *
     * @param str1
     * @param str2
     * @param length
     * @return
     */
    public static boolean leftEquals(String str1, String str2, int length) {
        return left(str1, length).equals(left(str2, length));
    }

    /**
     * 功能：将半角的符号转换成全角符号.(即英文字符转中文字符)
     *
     * @param str 源字符串
     * @return String
     * @date 2014年06月24日
     */
    public static String changeToFull(String str) {
        String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
        String[] decode = {"１", "２", "３", "４", "５", "６", "７", "８", "９", "０",
                "！", "＠", "＃", "＄", "％", "︿", "＆", "＊", "（", "）", "ａ", "ｂ",
                "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ",
                "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ",
                "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ",
                "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ",
                "Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；", "：",
                "'", "\"", "，", "〈", "。", "〉", "／", "？"};
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int pos = source.indexOf(str.charAt(i));
            if (pos != -1) {
                result += decode[pos];
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 功能：cs串中是否一个都不包含字符数组searchChars中的字符。
     *
     * @param cs          字符串
     * @param searchChars 字符数组
     * @return boolean 都不包含返回true，否则返回false。
     * @date 2014年06月24日
     */
    public static boolean containsNone(CharSequence cs, char... searchChars) {
        if (cs == null || searchChars == null) {
            return true;
        }
        int csLen = cs.length();
        int csLast = csLen - 1;
        int searchLen = searchChars.length;
        int searchLast = searchLen - 1;
        for (int i = 0; i < csLen; i++) {
            char ch = cs.charAt(i);
            for (int j = 0; j < searchLen; j++) {
                if (searchChars[j] == ch) {
                    if (Character.isHighSurrogate(ch)) {
                        if (j == searchLast) {
                            // missing low surrogate, fine, like
                            // String.indexOf(String)
                            return false;
                        }
                        if (i < csLast
                                && searchChars[j + 1] == cs.charAt(i + 1)) {
                            return false;
                        }
                    } else {
                        // ch is in the Basic Multilingual Plane
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * <p>
     * 编码为Unicode，格式 '\u0020'.
     * </p>
     *
     *
     *
     * <pre>
     *   CharUtils.unicodeEscaped(' ') = "\u0020"
     *   CharUtils.unicodeEscaped('A') = "\u0041"
     * </pre>
     *
     * @param ch 源字符串
     * @return 转码后的字符串
     * @date 2014年06月24日
     */
    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }

    /**
     * <p>
     * 进行tostring操作，如果传入的是null，返回空字符串。
     * </p>
     *
     * <pre>
     * ObjectUtils.toString(null)         = ""
     * ObjectUtils.toString("")           = ""
     * ObjectUtils.toString("bat")        = "bat"
     * ObjectUtils.toString(Boolean.TRUE) = "true"
     * </pre>
     *
     * @param obj 源
     * @return String
     */
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * <p>
     * 进行tostring操作，如果传入的是null，返回指定的默认值。
     * </p>
     *
     * <pre>
     * ObjectUtils.toString(null, null)           = null
     * ObjectUtils.toString(null, "null")         = "null"
     * ObjectUtils.toString("", "null")           = ""
     * ObjectUtils.toString("bat", "null")        = "bat"
     * ObjectUtils.toString(Boolean.TRUE, "null") = "true"
     * </pre>
     *
     * @param obj     源
     * @param nullStr 如果obj为null时返回这个指定值
     * @return String
     */
    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }

    /**
     * <p>
     * 只从源字符串中移除指定开头子字符串.
     * </p>
     *
     * <pre>
     * StringUtil.removeStart(null, *)      = null
     * StringUtil.removeStart("", *)        = ""
     * StringUtil.removeStart(*, null)      = *
     * StringUtil.removeStart("www.domain.com", "www.")   = "domain.com"
     * StringUtil.removeStart("domain.com", "www.")       = "domain.com"
     * StringUtil.removeStart("www.domain.com", "domain") = "www.domain.com"
     * StringUtil.removeStart("abc", "")    = "abc"
     * </pre>
     *
     * @param str    源字符串
     * @param remove 将要被移除的子字符串
     * @return String
     */
    public static String removeStart(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    /**
     * <p>
     * 只从源字符串中移除指定结尾的子字符串.
     * </p>
     *
     * <pre>
     * StringUtil.removeEnd(null, *)      = null
     * StringUtil.removeEnd("", *)        = ""
     * StringUtil.removeEnd(*, null)      = *
     * StringUtil.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtil.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtil.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtil.removeEnd("abc", "")    = "abc"
     * </pre>
     *
     * @param str    源字符串
     * @param remove 将要被移除的子字符串
     * @return String
     */
    public static String removeEnd(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * <p>
     * 将一个字符串重复N次
     * </p>
     *
     * <pre>
     * StringUtil.repeat(null, 2) = null
     * StringUtil.repeat("", 0)   = ""
     * StringUtil.repeat("", 2)   = ""
     * StringUtil.repeat("a", 3)  = "aaa"
     * StringUtil.repeat("ab", 2) = "abab"
     * StringUtil.repeat("a", -2) = ""
     * </pre>
     *
     * @param str    源字符串
     * @param repeat 重复的次数
     * @return String
     */
    public static String repeat2(String str, int repeat) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * <p>
     * 将一个字符串重复N次，并且中间加上指定的分隔符
     * </p>
     *
     * <pre>
     * StringUtil.repeat(null, null, 2) = null
     * StringUtil.repeat(null, "x", 2)  = null
     * StringUtil.repeat("", null, 0)   = ""
     * StringUtil.repeat("", "", 2)     = ""
     * StringUtil.repeat("", "x", 3)    = "xxx"
     * StringUtil.repeat("?", ", ", 3)  = "?, ?, ?"
     * </pre>
     *
     * @param str       源字符串
     * @param separator 分隔符
     * @param repeat    重复次数
     * @return String
     */
    public static String repeat(String str, String separator, int repeat) {
        if (str == null || separator == null) {
            return repeat(str, repeat);
        } else {
            // given that repeat(String, int) is quite optimized, better to rely
            // on it than try and splice this into it
            String result = repeat(str + separator, repeat);
            return removeEnd(result, separator);
        }
    }

    /**
     * <p>
     * 将某个字符重复N次.
     * </p>
     *
     * @param ch     某个字符
     * @param repeat 重复次数
     * @return String
     */
    public static String repeat(char ch, int repeat) {
        char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    /**
     * <p>
     * 字符串长度达不到指定长度时，在字符串右边补指定的字符.
     * </p>
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)     = null
     * StringUtil.rightPad("", 3, 'z')     = "zzz"
     * StringUtil.rightPad("bat", 3, 'z')  = "bat"
     * StringUtil.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtil.rightPad("bat", 1, 'z')  = "bat"
     * StringUtil.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     源字符串
     * @param size    指定的长度
     * @param padChar 进行补充的字符
     * @return String
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充指定字符
     * </p>
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)      = null
     * StringUtil.rightPad("", 3, "z")      = "zzz"
     * StringUtil.rightPad("bat", 3, "yz")  = "bat"
     * StringUtil.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtil.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtil.rightPad("bat", 1, "yz")  = "bat"
     * StringUtil.rightPad("bat", -1, "yz") = "bat"
     * StringUtil.rightPad("bat", 5, null)  = "bat  "
     * StringUtil.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str    源字符串
     * @param size   扩大后的长度
     * @param padStr 在右边补充的字符串
     * @return String
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充空格
     * </p>
     *
     * <pre>
     * StringUtil.leftPad(null, *)   = null
     * StringUtil.leftPad("", 3)     = "   "
     * StringUtil.leftPad("bat", 3)  = "bat"
     * StringUtil.leftPad("bat", 5)  = "  bat"
     * StringUtil.leftPad("bat", 1)  = "bat"
     * StringUtil.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  源字符串
     * @param size 扩大后的长度
     * @return String
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充指定的字符
     * </p>
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)     = null
     * StringUtil.leftPad("", 3, 'z')     = "zzz"
     * StringUtil.leftPad("bat", 3, 'z')  = "bat"
     * StringUtil.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtil.leftPad("bat", 1, 'z')  = "bat"
     * StringUtil.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     源字符串
     * @param size    扩大后的长度
     * @param padChar 补充的字符
     * @return String
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充指定的字符
     * </p>
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)      = null
     * StringUtil.leftPad("", 3, "z")      = "zzz"
     * StringUtil.leftPad("bat", 3, "yz")  = "bat"
     * StringUtil.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtil.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.leftPad("bat", 1, "yz")  = "bat"
     * StringUtil.leftPad("bat", -1, "yz") = "bat"
     * StringUtil.leftPad("bat", 5, null)  = "  bat"
     * StringUtil.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str    源字符串
     * @param size   扩大后的长度
     * @param padStr 补充的字符串
     * @return String
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * <p>
     * 扩大字符串长度并将现在的字符串居中，被扩大部分用空格填充。
     * <p>
     *
     * <pre>
     * StringUtil.center(null, *)   = null
     * StringUtil.center("", 4)     = "    "
     * StringUtil.center("ab", -1)  = "ab"
     * StringUtil.center("ab", 4)   = " ab "
     * StringUtil.center("abcd", 2) = "abcd"
     * StringUtil.center("a", 4)    = " a  "
     * </pre>
     *
     * @param str  源字符串
     * @param size 扩大后的长度
     * @return String
     */
    public static String center(String str, int size) {
        return center(str, size, ' ');
    }

    /**
     * <p>
     * 将字符串长度修改为指定长度，并进行居中显示。
     * </p>
     *
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, ' ')     = "    "
     * StringUtil.center("ab", -1, ' ')  = "ab"
     * StringUtil.center("ab", 4, ' ')   = " ab"
     * StringUtil.center("abcd", 2, ' ') = "abcd"
     * StringUtil.center("a", 4, ' ')    = " a  "
     * StringUtil.center("a", 4, 'y')    = "yayy"
     * </pre>
     *
     * @param str     源字符串
     * @param size    指定的长度
     * @param padChar 长度不够时补充的字符串
     * @return String
     * @throws IllegalArgumentException 如果被补充字符串为 null或者 empty
     */
    public static String center(String str, int size, char padChar) {
        if (str == null || size <= 0) {
            return str;
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padChar);
        str = rightPad(str, size, padChar);
        return str;
    }

    /**
     * <p>
     * 将字符串长度修改为指定长度，并进行居中显示。
     * </p>
     *
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, " ")     = "    "
     * StringUtil.center("ab", -1, " ")  = "ab"
     * StringUtil.center("ab", 4, " ")   = " ab"
     * StringUtil.center("abcd", 2, " ") = "abcd"
     * StringUtil.center("a", 4, " ")    = " a  "
     * StringUtil.center("a", 4, "yz")   = "yayz"
     * StringUtil.center("abc", 7, null) = "  abc  "
     * StringUtil.center("abc", 7, "")   = "  abc  "
     * </pre>
     *
     * @param str    源字符串
     * @param size   指定的长度
     * @param padStr 长度不够时补充的字符串
     * @return String
     * @throws IllegalArgumentException 如果被补充字符串为 null或者 empty
     */
    public static String center(String str, int size, String padStr) {
        if (str == null || size <= 0) {
            return str;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padStr);
        str = rightPad(str, size, padStr);
        return str;
    }

    /**
     * <p>
     * 检查字符串是否全部为小写.
     * </p>
     *
     * <pre>
     * StringUtil.isAllLowerCase(null)   = false
     * StringUtil.isAllLowerCase("")     = false
     * StringUtil.isAllLowerCase("  ")   = false
     * StringUtil.isAllLowerCase("abc")  = true
     * StringUtil.isAllLowerCase("abC") = false
     * </pre>
     *
     * @param cs 源字符串
     * @return String
     */
    public static boolean isAllLowerCase(String cs) {
        if (cs == null || isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLowerCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 检查是否都是大写.
     * </p>
     *
     * <pre>
     * StringUtil.isAllUpperCase(null)   = false
     * StringUtil.isAllUpperCase("")     = false
     * StringUtil.isAllUpperCase("  ")   = false
     * StringUtil.isAllUpperCase("ABC")  = true
     * StringUtil.isAllUpperCase("aBC") = false
     * </pre>
     *
     * @param cs 源字符串
     * @return String
     */
    public static boolean isAllUpperCase(String cs) {
        if (cs == null || StringUtil.isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isUpperCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 反转字符串.
     * </p>
     *
     * <pre>
     * StringUtil.reverse(null)  = null
     * StringUtil.reverse("")    = ""
     * StringUtil.reverse("bat") = "tab"
     * </pre>
     *
     * @param str 源字符串
     * @return String
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * <p>
     * 字符串达不到一定长度时在右边补空白.
     * </p>
     *
     * <pre>
     * StringUtil.rightPad(null, *)   = null
     * StringUtil.rightPad("", 3)     = "   "
     * StringUtil.rightPad("bat", 3)  = "bat"
     * StringUtil.rightPad("bat", 5)  = "bat  "
     * StringUtil.rightPad("bat", 1)  = "bat"
     * StringUtil.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  源字符串
     * @param size 指定的长度
     * @return String
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * 从右边截取字符串.</p>
     *
     * <pre>
     * StringUtil.right(null, *)    = null
     * StringUtil.right(*, -ve)     = ""
     * StringUtil.right("", *)      = ""
     * StringUtil.right("abc", 0)   = ""
     * StringUtil.right("abc", 2)   = "bc"
     * StringUtil.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str 源字符串
     * @param len 长度
     * @return String
     */
    public static String right2(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    /**
     * <p>
     * 截取一个字符串的前几个.
     * </p>
     *
     * <pre>
     * StringUtil.left(null, *)    = null
     * StringUtil.left(*, -ve)     = ""
     * StringUtil.left("", *)      = ""
     * StringUtil.left("abc", 0)   = ""
     * StringUtil.left("abc", 2)   = "ab"
     * StringUtil.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str 源字符串
     * @param len 截取的长度
     * @return the String
     */
    public static String left2(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    /**
     * <p>
     * 得到tag字符串中间的子字符串，只返回第一个匹配项。
     * </p>
     *
     * <pre>
     * StringUtil.substringBetween(null, *)            = null
     * StringUtil.substringBetween("", "")             = ""
     * StringUtil.substringBetween("", "tag")          = null
     * StringUtil.substringBetween("tagabctag", null)  = null
     * StringUtil.substringBetween("tagabctag", "")    = ""
     * StringUtil.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     *
     * @param str 源字符串。
     * @param tag 标识字符串。
     * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>
     * 得到两个字符串中间的子字符串，只返回第一个匹配项。
     * </p>
     *
     * <pre>
     * StringUtil.substringBetween("wx[b]yz", "[", "]") = "b"
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween(*, null, *)          = null
     * StringUtil.substringBetween(*, *, null)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "]")         = null
     * StringUtil.substringBetween("", "[", "]")        = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str   源字符串
     * @param open  起字符串。
     * @param close 末字符串。
     * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
     */
    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            int end = str.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    /**
     * <p>
     * 得到两个字符串中间的子字符串，所有匹配项组合为数组并返回。
     * </p>
     *
     * <pre>
     * StringUtil.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
     * StringUtil.substringsBetween(null, *, *)            = null
     * StringUtil.substringsBetween(*, null, *)            = null
     * StringUtil.substringsBetween(*, *, null)            = null
     * StringUtil.substringsBetween("", "[", "]")          = []
     * </pre>
     *
     * @param str   源字符串
     * @param open  起字符串。
     * @param close 末字符串。
     * @return String 子字符串数组, 如果没有符合要求的，返回{@code null}。
     */
    public static String[] substringsBetween(String str, String open,
                                             String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return new String[0];
        }
        int closeLen = close.length();
        int openLen = open.length();
        List<String> list = new ArrayList<String>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 功能：切换字符串中的所有字母大小写。<br/>
     *
     * <pre>
     * StringUtil.swapCase(null)                 = null
     * StringUtil.swapCase("")                   = ""
     * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     *
     * @param str 源字符串
     * @return String
     */
    public static String swapCase(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        char[] buffer = str.toCharArray();

        boolean whitespace = true;

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isLowerCase(ch)) {
                if (whitespace) {
                    buffer[i] = Character.toTitleCase(ch);
                    whitespace = false;
                } else {
                    buffer[i] = Character.toUpperCase(ch);
                }
            } else {
                whitespace = Character.isWhitespace(ch);
            }
        }
        return new String(buffer);
    }

    /**
     * 功能：截取出最后一个标志位之后的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     *
     * @param sourceStr 被截取的字符串
     * @param expr      分隔符
     * @return String
     * @date 2014年06月24日
     */
    public static String substringAfterLast(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }

        int pos = sourceStr.lastIndexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(pos + expr.length());
    }

    /**
     * 功能：截取出最后一个标志位之前的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     *
     * @param sourceStr 被截取的字符串
     * @param expr      分隔符
     * @return String
     * @date 2014年06月24日
     */
    public static String substringBeforeLast(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }
        int pos = sourceStr.lastIndexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(0, pos);
    }

    /**
     * 功能：截取出第一个标志位之后的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     *
     * @param sourceStr 被截取的字符串
     * @param expr      分隔符
     * @return String
     * @date 2014年06月24日
     */
    public static String substringAfter(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }

        int pos = sourceStr.indexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(pos + expr.length());
    }

    /**
     * 功能：截取出第一个标志位之前的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中存在不止一个，以第一个位置为准。
     *
     * @param sourceStr 被截取的字符串
     * @param expr      分隔符
     * @return String
     * @date 2014年06月24日
     */
    public static String substringBefore(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }
        int pos = sourceStr.indexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(0, pos);
    }

    /**
     * 功能：检查这个字符串是不是空字符串。<br/>
     * 如果这个字符串为null或者trim后为空字符串则返回true，否则返回false。
     *
     * @param chkStr 被检查的字符串
     * @return boolean
     * @date 2014年06月24日
     */
    public static boolean isEmpty2(String chkStr) {
        if (chkStr == null) {
            return true;
        } else {
            return "".equals(chkStr.trim());
        }
    }

    /**
     * 如果字符串没有超过最长显示长度返回原字符串，否则从开头截取指定长度并加...返回。
     *
     * @param str    原字符串
     * @param length 字符串最长显示的长度
     * @return 转换后的字符串
     */
    public static String trimString(String str, int length) {
        if (str == null) {
            return "";
        } else if (str.length() > length) {
            return str.substring(0, length - 3) + "...";
        } else {
            return str;
        }
    }


}
