package com.sociopath.component;

import com.sociopath.util.PathUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Description: Intro Panel
 *
 */
public class PanelIntroduction extends JPanel {
    private Image image;
    public PanelIntroduction() {
        try {
            // image prefer size 850 * 600
            image = ImageIO.read(new File(PathUtils.getPath("intro_img")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(400,500,1000,600);
        frame.add(new PanelIntroduction());

        frame.setVisible(true);
    }
}
