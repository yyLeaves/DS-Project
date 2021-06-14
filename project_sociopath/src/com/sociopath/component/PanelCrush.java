package com.sociopath.component;

import com.sociopath.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        bg_Panel=new JPanel();
        bg_Panel.setLayout(new BorderLayout());
        setBackground(new Color(0xEEA1C1));

        JButton teachButton = new JButton("Meet Your Crush and Stop the Rumor");
        teachButton.setPreferredSize(new Dimension(600,50));
        teachButton.setBackground(new Color(0xEEB0D2));


        add(teachButton,BorderLayout.NORTH);

        add(bg_Panel,BorderLayout.SOUTH);

        teachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                E5meetCrush e5 = new E5meetCrush(10,currentStudent);
                e5.meet(currentStudent);
            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLayout(new GridLayout());
        frame.setBounds(400,500,850,600);
        frame.add(new PanelCrush(E0Init.init(),(Integer)1));

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
