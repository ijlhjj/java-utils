package com.sweetmanor.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayUtilTest {

    @Test
    void testReverse() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {5, 4, 3, 2, 1};
        ArrayUtil.reverse(array);
        assertArrayEquals(expected, array);
    }

}
