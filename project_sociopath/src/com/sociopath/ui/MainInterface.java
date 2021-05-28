package com.sociopath.ui;

import com.sociopath.component.BackgroundPanel;
import com.sociopath.events.E0Init;
import com.sociopath.events.Student;
import com.sociopath.util.PathUtils;
import com.sociopath.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/12 21:58, in project com.sociopath.ui
 */
public class MainInterface {

    private ArrayList<Student> students = null;

    private JFrame frame = new JFrame("Sociopath");
    private JComboBox<String> selectStudent;

    final int WIDTH = 500;
    final int HEIGHT = 300;

    public MainInterface() {
        System.out.println("== Welcome! ==\n Please select your identity! Click 'See Graph' to see the relationships!\n");
    }

    public MainInterface(ArrayList<Student> students) {
        System.out.println("== Welcome Back! ==\n Please switch your identity! Click 'See Graph' to see the relationships!\n");
        this.students = students;
    }

    public void init() throws IOException{
        frame.setBounds(ScreenUtils.getScreenWidth()/2 - WIDTH/2, ScreenUtils.getScreenHeight()/2 - HEIGHT/2,WIDTH,HEIGHT);
        frame.setResizable(false);

        // set icon
        frame.setIconImage(ImageIO.read(new File(PathUtils.getPath("icon_image"))));

        // set background panel
        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File(PathUtils.getPath("bg_panel_image"))));

        frame.add(bgPanel);

        Box contentBox = Box.createVerticalBox();
        Box upBox = Box.createHorizontalBox();
        JLabel selectLabel = new JLabel("Select a student");

        selectStudent = new JComboBox<>();

        // add select item
        for (int i = 1; i < 11; i++) {
            selectStudent.addItem(""+i);
        }

        upBox.add(selectLabel);
        upBox.add(Box.createHorizontalStrut(20));
        upBox.add(selectStudent);

        Box downBox = Box.createHorizontalBox();
        JButton seeButton = new JButton("See Graph");
        seeButton.setSize(130,80);
        seeButton.setBackground(new Color(0xFFFFFFFF, true));
        JButton startButton = new JButton("start");
        startButton.setSize(60,80);
        startButton.setBackground(new Color(0xFFFFFF));
        downBox.add(seeButton);
        downBox.add(Box.createHorizontalStrut(40));
        downBox.add(startButton);

        contentBox.add(Box.createVerticalStrut(70));
        contentBox.add(upBox);
        contentBox.add(Box.createVerticalStrut(50));
        contentBox.add(downBox);

        bgPanel.add(contentBox);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        seeButton.addActionListener(new SeeGraphListener("relation_demo_image"));
        startButton.addActionListener(new StartListener());
    }


    public static void main(String[] args) throws IOException {

        new MainInterface().init();
    }

    class SeeGraphListener implements ActionListener {

        private String graphName;

        public SeeGraphListener(String graphName) {
            this.graphName = graphName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new GraphInterface(graphName, "Relation Graph").init();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedItem = (String)selectStudent.getSelectedItem();
            System.out.println("== Hello! User! ==\n You are currently playing as student "+ selectedItem+"\n");
            if(students==null) {
                students = E0Init.init();
                System.out.println("== Initializing... ==");
                Student.printStudents(students);
                System.out.println();
            }
            try {
//                new EventsInterface(selectedItem,students).init();
                new EventsInterface(students,Integer.parseInt((String) selectedItem)).init();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Student.setCurrentStudentId(Integer.parseInt((String) selectedItem));
            frame.dispose();
        }
    }

}



