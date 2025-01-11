package com.sweetmanor.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 使用 Commons IO 库的不进行测试
 */
class FileUtilTest {

    /**
     * 测试文件是否存在
     */
    @Test
    void testIsExist() {
        // 测试文件存在的情况
        assertTrue(FileUtil.isExist("src/main/java/com/sweetmanor/utils/FileUtil.java"));

        // 测试文件不存在的情况
        assertFalse(FileUtil.isExist("nonexistent.txt"));

        // 测试文件名为空的情况
        assertFalse(FileUtil.isExist(null));
    }

    /**
     * 测试获取文件的基本名称
     */
    @Test
    void testGetBaseName() {
        // 正常情况
        String fileName = "example.txt";
        String baseName = FileUtil.getBaseName(fileName);
        assertEquals("example", baseName);

        // 文件名只有扩展名
        fileName = ".txt";
        baseName = FileUtil.getBaseName(fileName);
        assertEquals("", baseName);

        // 空文件名
        fileName = "";
        baseName = FileUtil.getBaseName(fileName);
        assertEquals("", baseName);
    }

}
