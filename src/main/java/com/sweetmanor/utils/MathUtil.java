package com.sweetmanor.utils;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 数学计算工具类
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public final class MathUtil {

    /**
     * 私有构造方法，防止类被实例化
     */
    private MathUtil() {
    }

    /**
     * 返回数组中最大元素
     *
     * @param a 整数数组
     * @return 数组中的最大值
     */
    public static int max(int... a) {
        // 使用 Apache Commons Lang 库中的 NumberUtils.max 方法来计算数组中的最大值
        return NumberUtils.max(a);
    }

    /**
     * 判断一个整数是否为素数
     *
     * @param n 待判断的整数
     * @return 如果是素数返回 true，否则返回 false
     */
    public static boolean isPrime(int n) {
        // 如果小于2，直接返回false
        if (n < 2)
            return false;

        // 计算n的平方根，用于后续判断
        double last = Math.sqrt(n);

        // 从2到n的平方根进行遍历
        for (int i = 2; i <= last; i++)
            // 如果n能被i整除，说明不是素数，返回false
            if (n % i == 0)
                return false;

        // 如果没有找到能整除n的数，返回true
        return true;
    }

}
