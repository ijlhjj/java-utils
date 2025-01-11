package com.sweetmanor.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * 文件工具类：
 * 调用 commons-io 实现的方法更多的是一个使用示例，具体使用时尽量直接用原方法，这样可以减少封装不好而引入的不必要错误。
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public final class FileUtil {

    /**
     * 私有构造函数，用于阻止外部实例化该类
     */
    private FileUtil() {
    }

    /**
     * 判断指定文件是否存在
     *
     * @param filename 要检查的文件路径
     * @return 如果文件存在则返回 true，否则返回 false
     */
    public static boolean isExist(String filename) {
        // 如果文件名是 null，则返回 false
        if (filename == null)
            return false;
        // 创建一个文件对象
        File file = new File(filename);
        // 返回文件是否存在的结果
        return file.exists();
    }

    /**
     * 获取文件名，不包括扩展名
     *
     * @param fileName 要获取文件名的文件路径
     * @return 返回文件名，不包括扩展名
     */
    public static String getBaseName(String fileName) {
        // 使用 Apache Commons IO 库的 FilenameUtils 类的 getBaseName 方法获取文件名，不包括扩展名
        return FilenameUtils.getBaseName(fileName);
    }

    /**
     * 统计文件（夹）包含文件个数（递归所有子目录）
     *
     * @param dir 要统计的文件或目录
     * @return 文件个数
     */
    public static int countFiles(File dir) {
        // 使用 Apache Commons IO 库的 FileUtils 类的 listFiles 方法获取指定目录下的所有文件，包括子目录中的文件
        Collection<File> files = FileUtils.listFiles(dir, null, true);
        // 返回文件个数
        return files.size();
    }

    /**
     * 统计文件（夹）包含目录个数（递归所有子目录）
     *
     * @param dir 要统计的文件或目录
     * @return 目录个数
     */
    public static int countDirs(File dir) {
        // 使用 Apache Commons IO 库的 FileUtils 类的 listFilesAndDirs 方法获取指定目录下的所有目录，包括子目录中的目录
        Collection<File> files = FileUtils.listFilesAndDirs(dir, DirectoryFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        // 返回目录个数，减1是因为遍历结果包含了当前目录
        return files.size() - 1;
    }

    /**
     * 统计文件（夹）包含目录和文件个数（递归所有子目录）
     *
     * @param dir 要统计的文件或目录
     * @return 目录和文件的总个数
     */
    public static int countDirAndFiles(File dir) {
        // 使用 Apache Commons IO 库的 FileUtils 类的 listFilesAndDirs 方法获取指定目录下的所有文件和目录，包括子目录中的文件和目录
        Collection<File> files = FileUtils.listFilesAndDirs(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        // 返回目录和文件的总个数，减1是因为遍历结果包含了当前目录
        return files.size() - 1;
    }

    /**
     * 查找目录下指定类型文件
     *
     * @param dir        查找的目录
     * @param extensions 扩展名数组
     * @param recursive  是否递归遍历子目录，true遍历子目录，false只获取当前目录（不包括子目录）
     * @return 返回结果文件数组
     */
    public static File[] getFiles(File dir, String[] extensions, boolean recursive) {
        // 使用 Apache Commons IO 库的 FileUtils 类的 listFiles 方法获取指定目录下的指定扩展名文件
        Collection<File> files = FileUtils.listFiles(dir, extensions, recursive);
        // 将文件集合转换为数组
        return FileUtils.convertFileCollectionToFileArray(files);
    }

    /**
     * 文件拷贝
     * <strong>注意：</strong> 不能将目录拷贝到文件；拷贝过程将强制覆盖。
     *
     * @param sourceName 源文件路径
     * @param targetName 目标文件路径
     * @throws IOException 如果发生 I/O 错误
     */
    public static void copyFile(String sourceName, String targetName) throws IOException {
        // 将源文件路径和目标文件路径转换为 File 对象
        copyFile(new File(sourceName), new File(targetName));
    }

    /**
     * 文件拷贝
     * <strong>注意：</strong> 不能将目录拷贝到文件；拷贝过程将强制覆盖。
     *
     * @param source 源文件
     * @param target 目标文件或目录
     * @throws IOException 如果发生 I/O 错误
     */
    public static void copyFile(File source, File target) throws IOException {
        // 如果源文件是目录，而目标文件是普通文件，则抛出异常
        if (source.isDirectory() && target.isFile())
            throw new IOException("不能将目录拷贝到文件！");

        if (source.isDirectory() && target.isDirectory()) // 如果源文件是目录，而目标文件也是目录，则使用 FileUtils.copyDirectory 方法进行拷贝
            FileUtils.copyDirectory(source, target);
        else if (source.isFile() && target.isDirectory()) // 如果源文件是普通文件，而目标文件是目录，则使用 FileUtils.copyFileToDirectory 方法进行拷贝
            FileUtils.copyFileToDirectory(source, target);
        else if (source.isFile() && target.isFile()) // 如果源文件和目标文件都是普通文件，则使用 FileUtils.copyFile 方法进行拷贝
            FileUtils.copyFile(source, target);
    }

}
