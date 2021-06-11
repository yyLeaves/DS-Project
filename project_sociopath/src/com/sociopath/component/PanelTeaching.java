package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.E1Teaching;
import com.sociopath.events.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

/**
 * Description:
 *
 */
public class PanelTeaching extends JPanel {
    private JLabel label;

    private JPanel bg_Panel;

    public PanelTeaching(ArrayList<Student> students, Integer currentStudent) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(850,550));
        setBackground(new Color(0xEAF3DC));

        bg_Panel = new JPanel();

        JButton teachButton = new JButton("I wanna teach a stranger");
        teachButton.setPreferredSize(new Dimension(400,50));
        teachButton.setBackground(new Color(0xC8F1CC));

        add(teachButton,BorderLayout.NORTH);

        add(bg_Panel,BorderLayout.SOUTH);

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


            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLayout(new GridLayout());
        frame.setBounds(400,500,850,600);
        frame.add(new PanelTeaching(E0Init.init(), 1));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
