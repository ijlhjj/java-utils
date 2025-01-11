package com.sweetmanor.utils;

import java.util.Iterator;
import java.util.List;

/**
 * 集合工具类
 *
 * @author ijlhjj
 * @version 1.0 2023-09-03
 */
public final class CollectionUtil {

    private CollectionUtil() {
    }

    /**
     * 将一个整数列表转换为整数数组
     * 方法中使用 nums.get(i) 获取元素值，在链表中效率不高
     *
     * @param nums 要转换的整数列表，不能为 null
     * @return 转换后的整数数组，如果输入列表为 null，则返回一个空数组
     */
    public static int[] toArray(List<Integer> nums) {
        //入参不能为空
        if (nums == null)
            return new int[0];

        int[] arr = new int[nums.size()];

        for (int i = 0; i < nums.size(); i++)
            arr[i] = nums.get(i);

        return arr;
    }

    /**
     * 将一个整数列表转换为整数数组
     * 使用迭代器遍历列表，提高在链表上的访问效率
     *
     * @param nums 要转换的整数列表，不能为 null
     * @return 转换后的整数数组，如果输入列表为 null，则返回一个空数组
     */
    public static int[] toArray1(List<Integer> nums) {
        //入参不能为空
        if (nums == null)
            return new int[0];

        int[] arr = new int[nums.size()];

        int i = 0;
        // 使用迭代器遍历列表
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext()) {
            arr[i++] = it.next();
        }

        return arr;
    }

}
