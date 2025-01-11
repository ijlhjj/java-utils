package com.sweetmanor.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 对象序列化工具类：只作为一个代码示例，具体应用时应该使用成熟的第三方序列化库。
 *
 * @author ijlhjj
 * @version 1.0 2016-11-22
 */
public final class ObjectAccessUtil {
    private static final Logger logger = LoggerFactory.getLogger(ObjectAccessUtil.class);

    /**
     * 私有构造函数，防止类被实例化
     */
    private ObjectAccessUtil() {
    }

    /**
     * 将可序列化对象写入到文件中
     *
     * @param object 待写入的可序列化对象
     * @param file   写入的目标文件
     */
    public static void writeToFile(Serializable object, String file) {
        // 将字符串类型的文件路径转换为文件对象
        writeToFile(object, new File(file));
    }

    /**
     * 将可序列化对象写入到文件中
     *
     * @param object 待写入的可序列化对象
     * @param file   写入的目标文件
     */
    public static void writeToFile(Serializable object, File file) {
        // 检查对象和文件是否为空，如果为空则直接返回
        if (object == null || file == null)
            return;

        // 如果文件是目录，则直接返回
        if (file.isDirectory())
            return;

        // 如果文件已经存在，则先删除
        if (file.exists())
            file.delete();

        // 尝试创建文件的父目录
        try {
            FileUtils.forceMkdirParent(file);
        } catch (IOException e1) {
            logger.error("创建父目录失败：", e1);
        }

        // 尝试将对象写入到文件中
        try (FileOutputStream fos = new FileOutputStream(file); //
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(object);
        } catch (IOException e2) {
            logger.error("写入对象失败：", e2);
        }
    }

    /**
     * 从文件读取对象
     *
     * @param file 读取文件的名称
     * @return 反序列化读取到的对象，文件或对象不存在将返回null
     */
    public static Object readFromFile(String file) {
        // 将字符串类型的文件路径转换为文件对象
        return readFromFile(new File(file));
    }

    /**
     * 从文件读取对象
     *
     * @param file 读取的文件对象
     * @return 反序列化读取到的对象，文件或对象不存在将返回null
     */
    public static Object readFromFile(File file) {
        // 如果文件为空或不存在，则返回null
        if (file == null || !file.exists())
            return null;

        // 从输入流中读取对象并返回
        try (FileInputStream fis = new FileInputStream(file); //
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            return ois.readObject();
        } catch (Exception e) {
            logger.error("读取对象失败：", e);
        }

        // 如果发生异常，返回null
        return null;
    }

}
