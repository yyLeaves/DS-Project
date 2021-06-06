package com.sociopath.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/6/6 16:01, in project com.sociopath.component
 */
public class BoxLib extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;

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

        setBackground(new Color(0xffffff));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xffffff));
        buttonPanel.setMaximumSize(new Dimension(1500, 35));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton button1 = new JButton("Arrange Books");
        button1.setBackground(new Color(0xABDDEE));

        JButton button2 = new JButton("Meet Crush");
        button2.setBackground(new Color(0xEF75A9));

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // button for management
        add(buttonPanel);

        BookCalcPanel calcPanel = new BookCalcPanel();

//        RelationshipPanel relationshipPanel = new RelationshipPanel(students);

        add(calcPanel);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                remove(relationshipPanel);
                add(calcPanel);
                repaint();
            }

        });


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(calcPanel);
//                add(relationshipPanel);
//                relationshipPanel.validate();
                repaint();

            }
        });

    }
}