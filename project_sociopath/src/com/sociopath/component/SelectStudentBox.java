package com.sociopath.component;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/13 0:02, in project com.sociopath.component
 */
public class SelectStudentBox extends JComboBox {

    final int WIDTH = 60;
    final int HEIGHT = 20;

    public static void main(String[] args) {

    }

    public SelectStudentBox() {

        setSize(50,55);
        addItem("1");
        addItem("2");
        addItem("3");
        setBackground(new Color(0xFFFFFF));

    }
}
