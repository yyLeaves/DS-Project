package com.sociopath.util;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/12 22:25, in project com.sociopath.util
 */
public class PathUtils {

    private static final Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(new FileReader("path.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPath(String fileName) {
        String path;
//        Properties prop = new Properties();
        //            prop.load(new FileReader("path.properties"));
        path = prop.getProperty(fileName);
        return path;
//        return null;
    }

    public static void main(String[] args) {
        getPath("icon_image");
        File file = new File(getPath("icon_image"));
        System.out.println(file.getAbsolutePath());
    }
}
