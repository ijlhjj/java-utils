package com.sweetmanor.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 *
 * @author ijlhjj
 * @version 1.0 2024-12-01
 */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    /**
     * 逆序
     *
     * @param array 要逆序的数组
     */
    public static void reverse(int[] array) {
        // 使用 Apache Commons Lang 库的 ArrayUtils 类的 reverse 方法来逆序数组
        ArrayUtils.reverse(array);
    }

}
