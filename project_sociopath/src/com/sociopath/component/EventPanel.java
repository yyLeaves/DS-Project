package com.sociopath.component;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/13 19:39, in project com.sociopath.component
 */
public class EventPanel extends JPanel {

    public EventPanel() {
        setBackground(new Color(0xCBCBE8));
        setVisible(true);
    }

    public void init() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(0,0,850,600);
        frame.setBackground(new Color(0xE5E5F5));


        frame.setVisible(true);
    }
}
