package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Description: Library Panel
 *
 */
public class BoxLib extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;
    private ArrayList<Student> students;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 500, 300);
        frame.setBackground(new Color(0xffffff));
        frame.add(new BoxLib());

        frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public BoxLib() {
        super(BoxLayout.Y_AXIS);

        // original student list based on graph given
        students= E0Init.init();

        setBackground(new Color(0xffffff));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xffffff));
        buttonPanel.setMaximumSize(new Dimension(1500, 35));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton button1 = new JButton("Arrange Books");
        button1.setBackground(new Color(0xABDDEE));

        JButton button2 = new JButton("Meet Crush");
        button2.setBackground(new Color(0xF394BD));

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // button for management
        add(buttonPanel);

        PanelCalcBook calcPanel = new PanelCalcBook();

        // TODO: decide current student here
        PanelCrush crushPanel = new PanelCrush(students,Student.getCurrentStudentId());

//        RelationshipPanel relationshipPanel = new RelationshipPanel(students);

        add(calcPanel);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(crushPanel);
                add(calcPanel);
                repaint();
            }

        });


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(calcPanel);
                add(crushPanel);
                calcPanel.validate();
                validate();
                repaint();

            }
        });

    }
}