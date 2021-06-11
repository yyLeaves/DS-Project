package com.sociopath.component;

import javax.swing.*;
import java.awt.*;

/**
 * Description: background panel for relationship image
 *
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
