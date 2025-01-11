package com.sweetmanor.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证号码工具类
 *
 * <pre>
 *      身份证号码：由17位数字本体码和1位数字校验码组成。 从左至右依次为：6位数字地址码，8位数字出生日期码，3位数字顺序码和1位数字校验码。
 *          地址码：行政区划代码。
 *          出生日期码：出生年、月、日。
 *          顺序码：顺序码的奇数分配给男性，偶数分配给女性。
 *          校验码：根据前面17位数字码，按照 ISO7064: 1983.MOD11-2 校验码计算出来的检验码。
 *
 *      校验码计算方法:
 * 	        <ul>
 * 		        <li>将前面的身份证号码17位数分别乘以不同的系数。系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2。</li>
 * 		        <li>将这17位数字和系数相乘的结果相加。</li>
 * 		        <li>用加出来和除以11。</li>
 * 		        <li>余数只可能有0－1－2－3－4－5－6－7－8－9－10这11个数字。其分别对应的最后一位身份证的号码为1－0－X－9－8－7－6－5－4－3－2。</li>
 * 	        </ul>
 * </pre>
 *
 * @author ijlhjj
 * @version 1.0 2015-12-31
 */
public final class IDCardUtil {
    private static final Logger logger = LoggerFactory.getLogger(IDCardUtil.class);

    /**
     * 定义了一个常量数组，包含了身份证校验位的对应值
     */
    private static final String[] PARITY_BIT = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * 定义了一个常量数组，包含了计算身份证校验位的系数
     */
    private static final int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 定义了一个正则表达式，用于匹配 18 位身份证号码
     */
    private static final String CARD_PATTERN = "^\\d{17}[\\dxX]$";

    /**
     * 定义了一个正则表达式，用于匹配 15 位身份证号码
     */
    private static final String CARD15_PATTERN = "^\\d{15}$";

    /**
     * 定义了一个静态 Map 集合，用于存储省份代码和省份名称的对应关系
     */
    private static final Map<String, String> provinceMap;

    /*
      静态代码块，用于初始化省份代码和省份名称的对应关系
     */
    static {
        provinceMap = new HashMap<>();

        provinceMap.put("11", "北京");
        provinceMap.put("12", "天津");
        provinceMap.put("13", "河北");
        provinceMap.put("14", "山西");
        provinceMap.put("15", "内蒙古");
        provinceMap.put("22", "吉林");
        provinceMap.put("23", "黑龙江");
        provinceMap.put("31", "上海");
        provinceMap.put("32", "江苏");
        provinceMap.put("33", "浙江");
        provinceMap.put("34", "安徽");
        provinceMap.put("35", "福建");
        provinceMap.put("36", "江西");
        provinceMap.put("37", "山东");
        provinceMap.put("41", "河南");
        provinceMap.put("42", "湖北");
        provinceMap.put("43", "湖南");
        provinceMap.put("44", "广东");
        provinceMap.put("45", "广西");
        provinceMap.put("46", "海南");
        provinceMap.put("50", "重庆");
        provinceMap.put("51", "四川");
        provinceMap.put("52", "贵州");
        provinceMap.put("53", "云南");
        provinceMap.put("54", "西藏");
        provinceMap.put("61", "陕西");
        provinceMap.put("62", "甘肃");
        provinceMap.put("63", "青海");
        provinceMap.put("64", "宁夏");
        provinceMap.put("65", "新疆");
        provinceMap.put("71", "台湾");
        provinceMap.put("81", "香港");
        provinceMap.put("82", "澳门");
        provinceMap.put("91", "国外");
    }

    /**
     * 工具类，私有化构造函数
     */
    private IDCardUtil() {
    }

    /**
     * 对身份证号码做简单验证，其他内部方法都会调用此方法进行验证，验证流程如下：
     *
     * <pre>
     * 		<ul>
     * 			<li>标准化预处理；
     * 			<li>判断长度是否15或18位；
     * 			<li>判断是否符合身份证号模式匹配；
     * 			<li>判断省份是否为正确值。
     * 		</ul>
     * </pre>
     *
     * @param idCard 身份证号码，判断前会去除两边空格，所以两边包含空格的正确身份证号码也会返回true
     * @return 是否正确的身份证号码
     */
    public static boolean simpleCheck(String idCard) {
        // null值返回false
        if (idCard == null)
            return false;

        // 标准化处理，去除身份证号码中的空格
        idCard = standardizing(idCard);

        // 判断身份证号码的长度是否为15位或18位
        if (idCard.length() != 18 && idCard.length() != 15)
            return false;

        // 判断身份证号码是否符合18位或15位的正则表达式
        if (!idCard.matches(CARD_PATTERN) && !idCard.matches(CARD15_PATTERN))
            return false;

        // 判断省份代码是否在省份Map中
        String province = idCard.substring(0, 2);
        return provinceMap.containsKey(province);
    }

    /**
     * 获取身份证号码所在省，非法值返回null
     *
     * @return 例： 北京 / 河北
     */
    public static String getProvince(String idCard) {
        // 简单验证，防止非法入参
        if (!simpleCheck(idCard))
            return null;

        // 标准化处理，去除首尾空格
        idCard = standardizing(idCard);

        // 从省份Map中获取省份名称
        return provinceMap.get(idCard.substring(0, 2));
    }

    /**
     * 获取身份证号码性别，非法值返回null
     *
     * @return 男 / 女
     */
    public static String getGender(String idCard) {
        // 简单验证，防止非法入参
        if (!simpleCheck(idCard))
            return null;

        // 标准化处理，去除首尾空格
        idCard = standardizing(idCard);

        // 获取性别信息
        String gender = "";
        if (idCard.length() == 18)
            gender = idCard.substring(16, 17);
        else if (idCard.length() == 15)
            gender = idCard.substring(14, 15);

        // 判断并返回性别信息
        int num = Integer.parseInt(gender);
        return num % 2 == 0 ? "女" : "男";
    }

    /**
     * 获取身份证号码中的出生日期
     *
     * @param idCard 身份证号码
     * @return 出生日期，如果身份证号码格式不正确则返回 null
     */
    public static Date getBirthday(String idCard) {
        // 简单验证，防止非法入参
        if (!simpleCheck(idCard))
            return null;

        // 标准化处理，去除首尾空格
        idCard = standardizing(idCard);

        // 提取 出生日期 字符子串
        String birthday = "";
        if (idCard.length() == 18)
            birthday = idCard.substring(6, 14);
        else if (idCard.length() == 15)
            birthday = idCard.substring(6, 12);

        // 转换为日期对象并返回
        Date date = null;
        try {
            if (birthday.length() == 8)
                date = DateUtils.parseDateStrictly(birthday, "yyyyMMdd");    // 使用 yyyyMMdd 格式解析出生日期
            else if (birthday.length() == 6)
                date = DateUtils.parseDateStrictly(birthday, "yyMMdd");           // 使用 yyMMdd 格式解析出生日期
        } catch (ParseException e) {
            logger.error("身份证出生日期格式转换发生错误！错误信息：", e);
        }

        return date;
    }

    /**
     * 验证身份证号码的合法性
     *
     * @param idCard 身份证号码
     * @return 是否合法
     */
    public static boolean check(String idCard) {
        // 先做简单验证
        if (!simpleCheck(idCard))
            return false;

        // 标准化处理，去除首尾空格
        idCard = standardizing(idCard);

        // 校验出身日期合法性
        if (getBirthday(idCard) == null)
            return false;

        // 经过以上校验，15位身份证号被认为校验完成
        if (idCard.length() == 15)
            return true;

        // 18位身份证号校验，计算并比较校验位是否一致
        if (idCard.length() == 18) {
            String info = idCard.substring(0, 17);
            String check = idCard.substring(17);
            return check.equalsIgnoreCase(checkBit(info));
        }

        // 默认返回false
        return false;
    }

    /**
     * 计算身份证号码的最后一位校验位
     *
     * @param str 身份证号码的前 17 位字符
     * @return 校验位，如果输入的字符串不符合要求则返回空字符串
     */
    private static String checkBit(String str) {
        // 如果长度不符合要求，返回空字符串
        if (str == null || str.length() != 17)
            return "";

        // 检查输入的字符串是否全部由数字组成
        if (!NumberUtils.isDigits(str))
            return "";

        // 遍历字符串的每一位数字，根据权重计算总和
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            String bit = str.substring(i, i + 1);
            int b = Integer.parseInt(bit);
            sum += b * POWER_LIST[i];
        }

        // 根据余数返回对应的校验位字符
        int mod = sum % 11;
        return PARITY_BIT[mod];
    }

    /**
     * 将15位的身份证转换成18位身份证
     *
     * @param idCard15 15位身份证号码
     * @return 转换后的18位身份证号码，非法入参将返回null
     */
    public static String convert15to18IdCard(String idCard15) {
        if (idCard15 == null)
            return null;

        // 标准化处理，去除首尾空格
        idCard15 = standardizing(idCard15);

        // 如果身份证号码长度不是 15 位，则返回 null
        if (idCard15.length() != 15)
            return null;

        // 初始化 18 位身份证号码为空
        String idCard = null;

        // 入参必须全部为数字位
        if (NumberUtils.isDigits(idCard15)) {
            // 获取身份证号码中的出生日期
            Date birthday = getBirthday(idCard15);

            // 如果出生日期转换错误，则返回 null
            if (birthday == null)
                return null;

            // 拼接前 17 位信息，将 2 位年份转换为 4 位年份
            idCard = idCard15.substring(0, 6) + DateUtil.format(birthday, "yyyyMMdd") + idCard15.substring(12);

            // 计算校验位
            String checkBit = checkBit(idCard);

            // 拼接校验位
            idCard += checkBit;
        }

        // 返回转换后的 18 位身份证号码
        return idCard;
    }

    /**
     * 对身份证号码进行标准化处理
     * <pre>
     *     <ul>
     *         <li>null 值不进行处理；
     *         <li>两边去除空格；
     *         <li>如果有x转换为大写X；
     *     </ul>
     * </pre>
     *
     * @param idCard 待处理的身份证号码
     * @return 标准化处理后的身份证号码，如果输入为 null，则返回 null
     */
    public static String standardizing(String idCard) {
        if (idCard == null)
            return null;

        // 去除身份证号码两端的空格
        String result = idCard.trim();

        // 将身份证号码中的字母转换为大写
        result = result.toUpperCase();

        // 返回标准化处理后的身份证号码
        return result;
    }

}
