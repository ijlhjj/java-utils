package com.sweetmanor.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstTest {

    @Test
    void testClassPath() {
        // 验证 CLASS_PATH 是否正确设置
        assertNotNull(Const.CLASS_PATH);
    }

}
