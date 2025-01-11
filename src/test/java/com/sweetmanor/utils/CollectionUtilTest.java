package com.sweetmanor.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CollectionUtilTest {

    @Test
    void toArray() {
        // 正常情况
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        int[] arr1 = CollectionUtil.toArray(nums);
        int[] arr2 = CollectionUtil.toArray1(nums);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr1);
        assertArrayEquals(expected, arr2);

        // 空列表
        List<Integer> emptyNums = new ArrayList<>();

        int[] emptyExpected = new int[0];
        int[] emptyActual = CollectionUtil.toArray(emptyNums);

        assertArrayEquals(emptyExpected, emptyActual);

        // 空列表
        List<Integer> nullNums = null;

        int[] nullExpected = new int[0];
        int[] nullActual = CollectionUtil.toArray(nullNums);

        assertArrayEquals(nullExpected, nullActual);
    }

}
