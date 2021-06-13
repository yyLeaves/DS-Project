package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Description: Friend Box
 *
 */
public class BoxFriend extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;

    private ArrayList<Student> students;
    private int currentStudent;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 500, 300);
        frame.setBackground(new Color(0xffffff));
        frame.add(new BoxFriend(E0Init.init(),1));

        frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public BoxFriend(ArrayList<Student> students, int currentStudent) {
        super(BoxLayout.Y_AXIS);

        this.students = students;
        this.currentStudent=currentStudent;

        setBackground(new Color(0xffffff));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xffffff));
        buttonPanel.setMaximumSize(new Dimension(1500, 35));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton button1 = new JButton("Teach & Chitchat");
        button1.setBackground(new Color(0xABEEB2));

        JButton button2 = new JButton("Road to Glory");
        button2.setBackground(new Color(0xD2C3E7));

        JButton button3 = new JButton("Friendship");
        button3.setBackground(new Color(0xF6F8E0));

        JButton button4 = new JButton("Update LunchTime");
        button4.setBackground(new Color(0xF3E3E3));

        JButton button5 = new JButton("Crazy Big Eater");
        button5.setBackground(new Color(0xFF4D65));

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button5);
        buttonPanel.add(button4);


        // button for management
        add(buttonPanel);

        PanelTeaching teachPanel = new PanelTeaching(students,currentStudent);
        PanelLunch lunchPanel = new PanelLunch(students,currentStudent);
        PanelFriendship friendshipPanel = new PanelFriendship();
        PanelCrazyBigEater crazyBigEaterPanel = new PanelCrazyBigEater(students, currentStudent);


        add(teachPanel);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(lunchPanel);
                remove(friendshipPanel);
                remove(crazyBigEaterPanel);
                add(teachPanel);
                repaint();
            }

        });


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(teachPanel);
                remove(friendshipPanel);
                remove(crazyBigEaterPanel);
                add(lunchPanel);
                validate();
                lunchPanel.validate();
                repaint();

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(teachPanel);
                remove(lunchPanel);
                remove(crazyBigEaterPanel);
                add(friendshipPanel);
                validate();
                friendshipPanel.validate();
                repaint();

            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Update Lunchtime ==");

                System.out.println("* Old Lunchtime *");
                Student.printLunchtime(students);

                Student.updateLunchtime(students);
                System.out.println("* New Lunchtime *");
                Student.printLunchtime(students);
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(teachPanel);
                remove(friendshipPanel);
                remove(lunchPanel);
                add(crazyBigEaterPanel);
                validate();
                crazyBigEaterPanel.validate();
                repaint();

            }
        });
    }
}
