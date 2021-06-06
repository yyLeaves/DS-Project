package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.E1Teaching;
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
 * @author Yeyang Liu, S2000549
 * Created on: 2021/6/6 21:05, in project com.sociopath.component
 */
public class PanelLunch extends JPanel {


    private JLabel label;

    private JPanel bg_Panel;

    public PanelLunch(ArrayList<Student> students, Integer currentStudent) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(850,550));
        setBackground(new Color(0xEAF3DC));

        bg_Panel = new LunchBgPanel();
        bg_Panel.setBackground(Color.WHITE);
        bg_Panel.setPreferredSize(new Dimension(850,900));

        JButton teachButton = new JButton("How many reputations can I earn by having lunch?");
        teachButton.setPreferredSize(new Dimension(400,50));
        teachButton.setBackground(new Color(0xF1EACA));


        add(teachButton,BorderLayout.NORTH);

        add(bg_Panel,BorderLayout.SOUTH);

        teachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Your Road to Glory ==");

                E3Lunch lunch = new E3Lunch(students);
                ArrayList<Student> ans = lunch.receiver(students.get(currentStudent - 1));
                lunch.findMaxSolution();

                String s = lunch.getLunchList(ans);
                System.out.println(s);

                System.out.println(""+s);
                JOptionPane.showMessageDialog(null, s, "Your road to glory", JOptionPane.INFORMATION_MESSAGE);

            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLayout(new GridLayout());
        frame.setBounds(400,500,850,600);
        frame.add(new PanelLunch(E0Init.init(),(Integer)1));

        frame.setVisible(true);
    }
}

class LunchBgPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File(PathUtils.getPath("lunch_bg"))),0,0,getWidth(),getHeight(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}