package com.sweetmanor.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class IDCardUtilTest {

    /**
     * 测试simpleCheck方法，该方法用于简单验证身份证号码的合法性
     * 包括检查身份证号码的长度、是否为空、是否包含非法字符以及省份代码是否正确
     */
    @Test
    void testSimpleCheck() {
        // 正常的 18 位身份证号码
        assertTrue(IDCardUtil.simpleCheck("123456789012345678"));

        // 正常的 15 位身份证号码
        assertTrue(IDCardUtil.simpleCheck("123456789012345"));

        // 身份证号码前后有空格
        assertTrue(IDCardUtil.simpleCheck("123456789012345678  "));
        assertTrue(IDCardUtil.simpleCheck(" 123456789012345678 "));

        // 身份证号码为空
        assertFalse(IDCardUtil.simpleCheck(null));

        // 身份证号码长度不正确
        assertFalse(IDCardUtil.simpleCheck("12345678901234567"));

        // 身份证号码包含非法字符
        assertFalse(IDCardUtil.simpleCheck("123456789012345a"));

        // 身份证号码的省份代码不正确
        assertFalse(IDCardUtil.simpleCheck("103456789012345678"));
    }

    /**
     * 测试获取省份的方法
     * 该方法用于验证根据身份证号码获取省份的功能是否正确
     * 包括正确的 18 位和 15 位身份证号码，以及错误的身份证号码
     */
    @Test
    void testGetProvince() {
        // 获取身份证号码对应的省份
        assertEquals("北京", IDCardUtil.getProvince("110000199001011234"));

        // 获取身份证号码对应的省份（15 位）
        assertEquals("北京", IDCardUtil.getProvince("110000900101123"));

        // 获取身份证号码对应的省份（错误的身份证号码）
        assertNull(IDCardUtil.getProvince("103456789012345678"));
    }

    /**
     * 测试获取性别
     * 该方法用于验证根据身份证号码获取性别的功能是否正确
     * 包括正确的 18 位和 15 位身份证号码，以及错误的身份证号码
     */
    @Test
    void testGetGender() {
        // 测试 18 位身份证号，男性
        assertEquals("男", IDCardUtil.getGender("123456789012345678"));

        // 测试 18 位身份证号，女性
        assertEquals("女", IDCardUtil.getGender("123456789012345689"));

        // 测试 15 位身份证号，男性
        assertEquals("男", IDCardUtil.getGender("123456789012335"));

        // 测试 15 位身份证号，女性
        assertEquals("女", IDCardUtil.getGender("123456789012346"));

        // 测试非法长度的身份证号
        assertNull(IDCardUtil.getGender("12345678901234"));
    }

    /**
     * 测试获取生日
     * 该方法用于验证根据身份证号码获取生日的功能是否正确
     * 包括正确的 18 位和 15 位身份证号码，以及错误的身份证号码
     */
    @Test
    void testGetBirthday() throws ParseException {
        // 测试 18 位身份证号，正确的出生日期
        Date expectedBirthday1 = DateUtils.parseDateStrictly("19901231", "yyyyMMdd");
        assertEquals(expectedBirthday1, IDCardUtil.getBirthday("123456199012315678"));

        // 测试 15 位身份证号，正确的出生日期
        Date expectedBirthday2 = DateUtils.parseDateStrictly("901201", "yyMMdd");
        assertEquals(expectedBirthday2, IDCardUtil.getBirthday("123456901201345"));

        // 测试非法格式的身份证号
        String idCard3 = "12345678901234567a";
        assertNull(IDCardUtil.getBirthday(idCard3));
    }

    /**
     * 测试check方法，该方法用于验证身份证号码的合法性
     * 包括检查身份证号码的长度、是否为空、是否包含非法字符以及校验位是否正确
     */
    @Test
    void testCheck() {
        // 合法的 15 位身份证号码
        assertTrue(IDCardUtil.check("110101010101001"));

        // 合法的 18 位身份证号码
        assertTrue(IDCardUtil.check("110101199001011237"));

        // 不合法的身份证号码（长度不正确）
        assertFalse(IDCardUtil.check("12345"));

        // 不合法的身份证号码（校验位不正确）
        assertFalse(IDCardUtil.check("12345678901234567X"));

        // 空身份证号码
        assertFalse(IDCardUtil.check(""));

        // 包含非数字字符的身份证号码
        assertFalse(IDCardUtil.check("1234567890abcdef"));
    }

    /**
     * 测试convert15to18IdCard方法，该方法用于验证15位身份证号码转换为18位身份证号码的功能是否正确
     * 包括检查输入的身份证号码是否为空、是否为15位、是否包含非数字字符以及转换后的身份证号码是否正确
     */
    @Test
    void testConvert15to18IdCard() {
        // 测试空输入
        assertNull(IDCardUtil.convert15to18IdCard(null));

        // 测试非 15 位输入
        assertNull(IDCardUtil.convert15to18IdCard("12345"));

        // 测试包含非数字字符的输入
        assertNull(IDCardUtil.convert15to18IdCard("123abc"));

        // 测试正常的 15 位身份证号码转换
        assertEquals("110101200101010018", IDCardUtil.convert15to18IdCard("110101010101001"));

        // 测试出生日期转换错误的情况
        assertNull(IDCardUtil.convert15to18IdCard("123456789012346"));
    }

    /**
     * 测试standardizing方法，该方法用于验证身份证号码标准化的功能是否正确
     * 包括检查输入的身份证号码是否为空、是否为全空格、是否为正常身份证号码以及是否包含字母
     */
    @Test
    void testStandardizing() {
        // 测试空字符串
        assertEquals("", IDCardUtil.standardizing(""));

        // 测试全为空格的字符串
        assertEquals("", IDCardUtil.standardizing("   "));

        // 测试正常身份证号码
        assertEquals("123456789012345678", IDCardUtil.standardizing("123456789012345678"));

        // 测试包含字母的身份证号码
        assertEquals("11010119900101123X", IDCardUtil.standardizing("11010119900101123x "));
    }

}
