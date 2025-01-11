package com.sweetmanor.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PersonTest {

    @Test
    void testToString() {
        Person person = new Person("Alice", 25, "Female");
        String expected = "com.sweetmanor.common.Person [name=Alice, age=25, sex=Female]";
        String actual = person.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testEquals() {
        // 创建两个相同的 Person 对象
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Alice", 25);

        // 测试两个对象是否相等
        assertEquals(person1, person2);

        // 创建两个只有性别不同的 Person 对象
        Person person3 = new Person("Alice", 25, "Female");
        Person person4 = new Person("Alice", 25, "Male");

        // 测试两个对象是否相等
        assertEquals(person3, person4);

        // 测试与空对象比较
        assertNotEquals(person1, null);

        // 测试与不同类的对象比较
        assertNotEquals(person1, new Object());

        // 测试名字不同的情况
        Person differentNamePerson = new Person("Bob", 25);
        assertNotEquals(person1, differentNamePerson);

        // 测试年龄不同的情况
        Person differentAgePerson = new Person("Alice", 30);
        assertNotEquals(person1, differentAgePerson);
    }

    @Test
    void testHashCode() {
        // 创建 Person 对象
        Person person = new Person("Alice", 25);

        // 计算并验证哈希码
        int expectedHashCode = 31 * (31 + 25) + (person.getName() == null ? 0 : person.getName().hashCode());
        assertEquals(expectedHashCode, person.hashCode());

        // 测试空名字的情况
        person = new Person(null, 30);
        expectedHashCode = 31 * (31 + 30);
        assertEquals(expectedHashCode, person.hashCode());
    }

}
