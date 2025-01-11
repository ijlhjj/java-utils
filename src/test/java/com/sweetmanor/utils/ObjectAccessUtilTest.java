package com.sweetmanor.utils;

import com.sweetmanor.common.Person;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 全部测试方法使用一个测试实例，测试必须按指定顺序执行
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ObjectAccessUtilTest {

    private File file;

    private Person p;

    /**
     * 在测试开始前创建一个临时文件
     * 只有在使用 @TestInstance(Lifecycle.PER_CLASS) 时，@BeforeAll 才可以不使用 static 修饰
     *
     * @throws IOException 如果在创建临时文件时发生错误，则抛出此异常
     */
    @BeforeAll
    void setUp() throws IOException {
        file = File.createTempFile("person-", ".dat");
        assertTrue(file.exists());
    }

    /**
     * 在测试结束后删除临时文件
     * 只有在使用 @TestInstance(Lifecycle.PER_CLASS) 时，@AfterAll 才可以不使用 static 修饰
     */
    @AfterAll
    void tearDown() {
        // 如果文件存在，则删除
        if (file.exists()) {
            file.delete();
        }
        // 断言文件不存在
        assertFalse(file.exists());
    }

    /**
     * 测试写入序列化对象到文件中
     */
    @Test
    @Order(1)
    void testWriteToFile() {
        // 创建一个Person对象
        p = new Person("孙悟空", 500, "男");
        // 将Person对象写入到文件中
        ObjectAccessUtil.writeToFile(p, file);
        // 断言文件存在
        assertTrue(file.exists());
    }

    /**
     * 测试从文件中读取序列化对象，必须在对象写入文件后执行
     */
    @Test
    @Order(2)
    void testReadFromFile() {
        // 从文件中读取序列化对象
        Person p2 = (Person) ObjectAccessUtil.readFromFile(file);
        // 断言读取到的对象不为空
        assertNotNull(p2);
        // 断言读取到的对象与原对象相等
        assertEquals(p, p2);
        // 断言读取到的对象的 sex 属性为空，因为该属性在序列化时被标记为 transient
        assertNull(p2.getSex());
    }

}
