package com.sweetmanor.common;

import java.util.Random;

/**
 * 随机整型数组生成器
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public final class RandomInt {
    static final int MAX = 100; // 默认生成最大值（不包括）
    static final int COUNT = 10;// 默认生成元素个数
    public static final int LITTLE_ARRAY = 10000;
    public static final int MEDIUM_ARRAY = 1_000_000;
    public static final int LARGE_ARRAY = 100_000_000;

    private static final Random random = new Random();

    private RandomInt() {
    }

    /**
     * 生成默认随机数组
     */
    public static int[] random() {
        return random(COUNT);
    }

    /**
     * 生成数组大小为count的随机数组
     *
     * @param count 生成元素个数
     * @return 返回生成的随机数组
     */
    public static int[] random(int count) {
        return random(count, MAX);
    }

    /**
     * 生成数组大小为 LITTLE_ARRAY 的随机数组
     */
    public static int[] getLittleArray() {
        return random(LITTLE_ARRAY, Integer.MAX_VALUE);
    }

    /**
     * 生成数组大小为 MEDIUM_ARRAY 的随机数组
     */
    public static int[] getMediumArray() {
        return random(MEDIUM_ARRAY, Integer.MAX_VALUE);
    }

    /**
     * 生成数组大小为 LARGE_ARRAY 的随机数组
     */
    public static int[] getLargeArray() {
        return random(LARGE_ARRAY, Integer.MAX_VALUE);
    }

    /**
     * 生成包含count个元素，最大值为max(不包括)的随机数组
     *
     * @param count 生成元素个数，count小于1时返回空数组(int[0])
     * @param max   最大值(不包括)
     * @return 返回生成的随机数组
     */
    public static int[] random(int count, int max) {
        // 如果count小于1，则返回空数组
        if (count < 1)
            return new int[0];

        // 创建一个长度为count的整型数组
        int[] intArray = new int[count];

        // 遍历数组，为每个元素随机赋值
        for (int i = 0; i < count; i++)
            // 生成一个小于max的随机整数，并赋值给数组的第i个元素
            intArray[i] = random.nextInt(max);

        // 返回生成的随机数组
        return intArray;
    }

    /**
     * 生成包含count个元素，下限为 lower(包括)，上限为 upper(不包括)的随机数组
     *
     * @param count 生成元素个数
     * @param lower 下限(包括)
     * @param upper 上限(不包括)
     * @return 返回生成的随机数组
     * @throws IllegalArgumentException 元素个数小于0或者下限大于等于上限
     * @since 1.8
     */
    public static int[] random(long count, int lower, int upper) {
        // 检查参数是否合法
        if (count < 0 || lower >= upper) {
            // 如果参数不合法，抛出 IllegalArgumentException 异常
            throw new IllegalArgumentException("Invalid parameters: count = " + count + ", lower = " + lower + ", upper = " + upper);
        }

        // 使用 IntStream 生成随机整数流，并转换为数组
        return random.ints(count, lower, upper).toArray();
    }

}
