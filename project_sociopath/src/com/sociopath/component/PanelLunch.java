package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.E3Lunch;
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
public class PanelLunch extends JPanel {


    private JLabel label;

    private JPanel bg_Panel;

    public PanelLunch(ArrayList<Student> students, Integer currentStudent) {

        setLayout(new BorderLayout());
        setBackground(new Color(0xE8DCF3));

        bg_Panel = new JPanel();
        bg_Panel.setBackground(new Color(0xE8DCF3));

        JButton teachButton = new JButton("How many reputations can I earn by having lunch?");
        teachButton.setPreferredSize(new Dimension(400,50));
        teachButton.setBackground(new Color(0xCCA6EE));


        add(teachButton,BorderLayout.NORTH);

        add(bg_Panel,BorderLayout.SOUTH);

        teachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Your Road to Glory ==");

                E3Lunch lunch = new E3Lunch(students,students.get(currentStudent-1));

                String s = E3Lunch.getLunchList(E3Lunch.findMaxSolution());

                System.out.println(s);

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
