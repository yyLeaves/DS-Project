package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.CrazyBigEater;
import com.sociopath.events.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Description: Lunch Panel
 *
 */
public class PanelCrazyBigEater extends JPanel {


    private JLabel label;

    private JPanel bg_Panel;

    public PanelCrazyBigEater(ArrayList<Student> students, Integer currentStudent) {

        setLayout(new BorderLayout());
        setBackground(new Color(0xFF8B8B));

        bg_Panel = new JPanel();
        bg_Panel.setBackground(new Color(0xFF8B8B));

        JButton teachButton = new JButton("How many reputations can I earn by Crazy Big Eater?");
        teachButton.setPreferredSize(new Dimension(400,50));
        teachButton.setBackground(new Color(0xFF4D65));


        add(teachButton,BorderLayout.NORTH);

        add(bg_Panel,BorderLayout.SOUTH);

        teachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Crazy Big Eater ==");

                CrazyBigEater lunch = new CrazyBigEater(students);

                String s = CrazyBigEater.getLunchList(CrazyBigEater.receiver(students.get(currentStudent-1)),students.get(currentStudent-1));

                System.out.println(s);

                JOptionPane.showMessageDialog(null, s, "Crazy Big Eater", JOptionPane.INFORMATION_MESSAGE);

            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLayout(new GridLayout());
        frame.setBounds(400,500,850,600);
        frame.add(new PanelCrazyBigEater(E0Init.init(),(Integer)1));

        frame.setVisible(true);
    }
}
