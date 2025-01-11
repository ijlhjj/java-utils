package com.sweetmanor.utils;

import java.awt.*;

/**
 * 窗体工具类
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public final class FrameUtil {

    /**
     * 私有构造方法，防止类被实例化
     */
    private FrameUtil() {
    }

    /**
     * 设置frame居中于桌面
     *
     * @param frame 要居中的容器
     */
    public static void center(Container frame) {
        // 调用另一个center方法，将桌面作为父容器
        center(null, frame);
    }

    /**
     * 设置frame居中于容器owner
     *
     * @param owner 父容器，如果为null，则默认为屏幕
     * @param frame 要居中的容器
     */
    public static void center(Container owner, Container frame) {
        // 如果要设置的窗体为空，则直接返回
        if (frame == null)
            return;

        // 外层容器的坐标和大小默认设置为屏幕的相应属性
        int x = 0;
        int y = 0;
        Dimension screen = frame.getToolkit().getScreenSize();

        // 如果外层容器不为空
        if (owner != null) {
            // 坐标偏移初始值为容器的坐标值
            x = owner.getX();
            y = owner.getY();
            // 设置外层容器大小
            screen = new Dimension(owner.getWidth(), owner.getHeight());
        }

        // 设置位置为内外容器差的一半加上坐标偏移值
        frame.setLocation((screen.width - frame.getSize().width) / 2 + x, (screen.height - frame.getSize().height) / 2 + y);
    }

}
