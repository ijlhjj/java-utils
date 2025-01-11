package com.sweetmanor.common;

import java.util.Objects;

/**
 * 常用常量定义
 */
public final class Const {

    /**
     * 类加载根目录
     */
    public static final String CLASS_PATH;

    private Const() {
    }

    static {
        // 获取类加载器的根路径
        String path = Objects.requireNonNull(Const.class.getResource("/")).getPath();
        // 某些系统下路径会以“/”开头，去除这个斜杠
        if (path.startsWith("/"))
            path = path.substring(1);
        // 初始化 CLASS_PATH 常量
        CLASS_PATH = path;
    }

}
