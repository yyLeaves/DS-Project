package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/26 21:03, in project com.sociopath.component
 */
public class StudentInfoBox extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;

    private ArrayList<Student> students;


    public StudentInfoBox(int axis, ArrayList<Student> students) {
        super(BoxLayout.Y_AXIS);

        setBackground(new Color(0xffffff));

        this.students = students;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xffffff));
        buttonPanel.setMaximumSize(new Dimension(1500, 35));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton button1 = new JButton("General Statistics");
        button1.setBackground(new Color(0xE3F8F5));

        JButton button2 = new JButton("Relationship");
        button2.setBackground(new Color(0xE1D7F6));

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // button for management
        add(buttonPanel);

        StatPanel infoTablePanel = new StatPanel(students);

        RelationshipPanel relationshipPanel = new RelationshipPanel(students);

        add(infoTablePanel);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(relationshipPanel);
                add(infoTablePanel);
                repaint();
            }

        });


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(infoTablePanel);
                add(relationshipPanel);
                relationshipPanel.validate();
                repaint();

            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 500, 300);
        frame.setBackground(new Color(0xffffff));
        frame.add(new StudentInfoBox(130, E0Init.init()));

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}

