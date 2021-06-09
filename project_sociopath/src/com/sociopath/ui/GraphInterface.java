package com.sociopath.ui;

import com.sociopath.util.PathUtils;
import com.sociopath.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/13 16:14, in project com.sociopath.ui
 */
public class GraphInterface extends JFrame {

    final int WIDTH = 1000;
    final int HEIGHT = 600;

    private String graphName;

    public GraphInterface(String graphName, String title) {
        super(title);
        this.graphName = graphName;
    }

    public GraphInterface(String graphName) {
        super("Graph");
        this.graphName = graphName;
    }

    public void init() throws IOException {
        setBounds(ScreenUtils.getScreenWidth()/2 - WIDTH/2, ScreenUtils.getScreenHeight()/2 - HEIGHT/2,WIDTH,HEIGHT);

        JPanel panel = new JPanel();

        setIconImage(ImageIO.read(new File(PathUtils.getPath("graph_icon_image"))));

        JLabel imageLabel = new JLabel();

        ImageIcon displayImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(PathUtils.getPath(graphName)));

//        displayImageIcon.setImage(displayImageIcon.getImage().getScaledInstance(110,50,Image.SCALE_DEFAULT));

        imageLabel.setIcon(displayImageIcon);

        panel.add(imageLabel);

        add(panel);

        setVisible(true);

        // won't close other windows in close operation
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args) throws IOException {
        new GraphInterface("relation_demo_image").init();
    }
}
