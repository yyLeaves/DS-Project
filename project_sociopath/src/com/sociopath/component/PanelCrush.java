package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.GraphVisted;
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
//                new PanelCrush2(currentStudent).init();
//                add(new PanelCrush2(students,Student.getCurrentStudentId()));

                // Call method here
/*                JOptionPane.showMessageDialog(null, "Please Input in Terminal", "Meet your Crush", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("== Meet Your Crush ==");

                //TODO : add here
                GraphVisted.t();
                JOptionPane.showMessageDialog(null, "Please Input in Terminal", "Meet your Crush", JOptionPane.INFORMATION_MESSAGE);*/

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
