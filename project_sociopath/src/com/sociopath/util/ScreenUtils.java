package com.sociopath.util;

import java.awt.*;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/12 22:02, in project com.sociopath.util
 */
public class ScreenUtils {

    private static final int WIDTH;
    private static final int HEIGHT;

    static {
        WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    }


    /**
     * get the width of user's screen
     * @return width of screen
     */
    public static int getScreenWidth() {
//        return Toolkit.getDefaultToolkit().getScreenSize().width;
        return WIDTH;
    }
    /**
     * get the height of user's screen
     * @return height of screen
     */
    public static int getScreenHeight() {
//        return Toolkit.getDefaultToolkit().getScreenSize().height;
        return HEIGHT;
    }
}
