package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.E3Lunch;
import com.sociopath.events.Student;
import com.sociopath.util.PathUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Description:
 *
 */
public class PanelCrush extends JPanel {


    private JLabel label;

    private JPanel bg_Panel;

    public PanelCrush(ArrayList<Student> students, Integer currentStudent) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(850,550));
        setBackground(new Color(0xEAF3DC));

        bg_Panel = new CrushBgPanel();
        bg_Panel.setBackground(Color.WHITE);
        bg_Panel.setPreferredSize(new Dimension(850,900));

        JButton teachButton = new JButton("Meet Your Crush and Stop the Rumor");
        teachButton.setPreferredSize(new Dimension(400,50));
        teachButton.setBackground(new Color(0xF1EACA));


        add(teachButton,BorderLayout.NORTH);

        add(bg_Panel,BorderLayout.SOUTH);

        teachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Meet Your Crush ==");

                // TODO: 2021/6/7

                // Call method here


                JOptionPane.showMessageDialog(null, "Implementing", "Your road to glory", JOptionPane.INFORMATION_MESSAGE);

            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLayout(new GridLayout());
        frame.setBounds(400,500,850,600);
        frame.add(new PanelCrush(E0Init.init(),(Integer)1));

        frame.setVisible(true);
    }
}
class CrushBgPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File(PathUtils.getPath("crush_bg"))),0,0,getWidth(),getHeight(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}