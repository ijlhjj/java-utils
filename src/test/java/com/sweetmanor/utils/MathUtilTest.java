package com.sweetmanor.utils;

import cn.hutool.core.util.NumberUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilTest {

    /**
     * 测试素数判断方法
     */
    @Test
    void testIsPrime() {
        // 测试小于 2 的数
        assertFalse(MathUtil.isPrime(1));
        assertFalse(MathUtil.isPrime(0));
        assertFalse(MathUtil.isPrime(-1));

        // 测试边界情况，如最大整数
        assertTrue(MathUtil.isPrime(Integer.MAX_VALUE));

        for (int i = 2; i < 10000; i++) {
            // 断言 NumberUtil.isPrimes(i) 和 MathUtil.isPrime(i) 的结果相等
            assertEquals(NumberUtil.isPrimes(i), MathUtil.isPrime(i));
        }
    }

}
