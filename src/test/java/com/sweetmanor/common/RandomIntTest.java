package com.sweetmanor.common;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomIntTest {

    /**
     * random方法返回数组大小和最大值符合预期
     */
    @Test
    void testRandom() {
        int[] result = RandomInt.random();
        assertNotNull(result);
        assertEquals(RandomInt.COUNT, result.length);

        int max = NumberUtils.max(result);
        assertTrue(max < RandomInt.MAX);
    }

    /**
     * random(int)方法返回数组大小和最大值符合预期
     */
    @Test
    void testRandomInt() {
        // 测试正常情况
        int[] result = RandomInt.random(100);
        assertNotNull(result);
        assertEquals(100, result.length);

        int max = NumberUtils.max(result);
        assertTrue(max < RandomInt.MAX);

        // 测试边界情况，count 为 0
        result = RandomInt.random(0);
        assertNotNull(result);
        assertEquals(0, result.length);

        // 测试边界情况，count 为负数
        result = RandomInt.random(-1);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * getLittleArray方法返回数组大小符合预期
     */
    @Test
    void testGetLittleArray() {
        int[] result = RandomInt.getLittleArray();

        // 检查数组长度
        assertNotNull(result);
        assertEquals(RandomInt.LITTLE_ARRAY, result.length);

        // 检查数组中的元素是否在合法范围内
        int min = NumberUtils.min(result);
        assertTrue(min >= 0);

        int max = NumberUtils.max(result);
        assertTrue(max < Integer.MAX_VALUE);
    }

    /**
     * getMediumArray方法返回数组大小符合预期
     */
    @Test
    void testGetMediumArray() {
        int[] result = RandomInt.getMediumArray();

        // 检查数组长度
        assertNotNull(result);
        assertEquals(RandomInt.MEDIUM_ARRAY, result.length);

        // 检查数组中的元素是否在合法范围内
        int min = NumberUtils.min(result);
        assertTrue(min >= 0);

        int max = NumberUtils.max(result);
        assertTrue(max < Integer.MAX_VALUE);
    }

    /**
     * getLargeArray方法返回数组大小符合预期
     */
    @Test
    void testGetLargeArray() {
        int[] result = RandomInt.getLargeArray();

        // 检查数组长度
        assertNotNull(result);
        assertEquals(RandomInt.LARGE_ARRAY, result.length);

        // 检查数组中的元素是否在合法范围内
        int min = NumberUtils.min(result);
        assertTrue(min >= 0);

        int max = NumberUtils.max(result);
        assertTrue(max < Integer.MAX_VALUE);
    }

    /**
     * random(int, int)方法返回数组大小和最大值符合预期
     */
    @Test
    void testRandomWithValidCountAndMax() {
        // 测试正常情况
        int count = 10000;
        int limit = 1000;

        int[] result = RandomInt.random(count, limit);

        // 检查数组长度
        assertNotNull(result);
        assertEquals(count, result.length);

        // 检查数组中的元素是否在合法范围内
        int min = NumberUtils.min(result);
        assertTrue(min >= 0);

        int max = NumberUtils.max(result);
        assertTrue(max < limit);
    }

    @Test
    void testRandomWithZeroCount() {
        int count = 0;
        int max = 10;

        int[] result = RandomInt.random(count, max);

        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    void testRandomWithNegativeCount() {
        int count = -5;
        int max = 10;

        int[] result = RandomInt.random(count, max);

        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * random(long, int, int)方法返回数组大小和上下限符合预期
     */
    @Test
    void testRandomThreeArgs() {
        // 正常情况测试
        long count = 10000L;
        int lower = 100;
        int upper = 100_000_000;

        int[] result1 = RandomInt.random(count, lower, upper);

        // 检查数组长度
        assertNotNull(result1);
        assertEquals(count, result1.length);

        // 检查数组中的元素是否在合法范围内
        int min = NumberUtils.min(result1);
        assertTrue(lower <= min);
        int max = NumberUtils.max(result1);
        assertTrue(max < upper);

        // 边界情况测试
        int[] result2 = RandomInt.random(1, 1, 2);
        assertNotNull(result2);
        assertEquals(1, result2.length);
        assertEquals(1, result2[0]);

        // 异常情况测试
        assertThrows(IllegalArgumentException.class, () -> RandomInt.random(-1, 1, 10));
        assertThrows(IllegalArgumentException.class, () -> RandomInt.random(10, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> RandomInt.random(10, 10, 1));
    }

}
