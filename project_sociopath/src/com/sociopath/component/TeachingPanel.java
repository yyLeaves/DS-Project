package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.E1Teaching;
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
 * Created on: 2021/5/28 1:06, in project com.sociopath.component
 */
public class TeachingPanel extends JPanel {

    private ArrayList<String> chats;

    private JLabel label;

    public TeachingPanel(ArrayList<Student> students, Integer currentStudent) {
        setLayout(new FlowLayout());
        setSize(850,600);
        setBackground(new Color(0xEAF3DC));

        JButton teachButton = new JButton("I wanna teach a stranger");
        teachButton.setBackground(new Color(0xF5E2ED));
        add(teachButton);

        teachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Teaching & Chit Chat ==");
                E1Teaching teach = new E1Teaching(students);
                String s;

                Student student = teach.randomStrangerToHelp(currentStudent);
                if(student==null) {
                    s="You have made friends with everyone! ==";
                } else {
                    s = teach.teachAStranger(currentStudent);
                }
                System.out.println(""+s);
                JOptionPane.showMessageDialog(null, s, "TEACHING!", JOptionPane.INFORMATION_MESSAGE);
//                label = new JLabel(s);
//                add(label);
//                repaint();
            }
        });



    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setBounds(400,500,1000,600);
        frame.add(new TeachingPanel(E0Init.init(),(Integer)1));

        frame.setVisible(true);
    }
}
