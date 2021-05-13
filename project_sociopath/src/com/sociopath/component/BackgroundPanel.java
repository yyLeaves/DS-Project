package com.sociopath.component;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/12 23:25, in project com.sociopath.component
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    public BackgroundPanel(Image bgImage) {
        backgroundImage = bgImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),null);
    }
}
