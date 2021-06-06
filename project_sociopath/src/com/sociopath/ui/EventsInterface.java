package com.sociopath.ui;

import com.sociopath.component.*;
import com.sociopath.events.E0Init;
import com.sociopath.events.Student;
import com.sociopath.util.PathUtils;
import com.sociopath.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Description:
 *
 */
public class EventsInterface extends JFrame {

    final int WIDTH = 1000;
    final int HEIGHT = 600;

//    private String userName = "Student";
    private ArrayList<Student> students = null;

    public EventsInterface(ArrayList<Student>students, Integer id) {
        Student.setStudents(students);
        this.students = Student.getStudents();
        Student.setCurrentStudentId(id);
    }

    public EventsInterface(Student student) {
        this.students = Student.getStudents();
        System.out.println(" "+Student.getCurrentStudentId());
    }

    public void initStudents() {
        students = E0Init.init();
        System.out.println("== Initializing... ==");
        Student.printStudents(students);
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
        new EventsInterface(E0Init.init(),Student.getCurrentStudentId()).init();
    }

    public void init() throws IOException {
        /*if (students == null) {
            initStudents();
        }*/
//        System.out.println(Student.getCurrentStudentId());
        this.setTitle("Hello, Student" + Student.getCurrentStudentId() + "!");
        setBounds(ScreenUtils.getScreenWidth() / 2 - WIDTH / 2, ScreenUtils.getScreenHeight() / 2 - HEIGHT / 2, WIDTH, HEIGHT);
//        setResizable(true);
        setResizable(false);
        setIconImage(ImageIO.read(new File(PathUtils.getPath("event_icon_image"))));

        // set menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu settingMenu = new JMenu("Settings");
        JMenuItem switchItem = new JMenuItem("Switch");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem initItem = new JMenuItem("Initialize");
        switchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface(students).init();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                dispose();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Bye Bye ==\nヾ(￣▽￣)Bye~Bye~");
                System.exit(0);
            }
        });
        initItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("== Initialize ==\n You will be logged out and the history will be cleared permanently. The data will be initialized. Please Confirm!\n");


                int initOrNot = JOptionPane.showConfirmDialog(null, "The history will be permanently cleared, are you sure you want to init?",
                        "Initialize Warning", JOptionPane.YES_NO_OPTION);
                if(initOrNot==0) {
                    Student.resetCount();
                    students = E0Init.init();
                    System.out.println("== Initializing ==");
                    Student.printStudents(students);
                    System.out.println();
                    try {
                        new MainInterface(students).init();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    dispose();
                }
            }
        });

        settingMenu.add(switchItem);
        settingMenu.add(exitItem);
        settingMenu.add(initItem);

        menuBar.add(settingMenu);

//        add(menuBar);
        setJMenuBar(menuBar);

        // split pane
        JSplitPane pane = new JSplitPane();
        pane.setContinuousLayout(true);
        pane.setDividerLocation(150);
        pane.setDividerSize(5);


        // left panel
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Events");
        DefaultMutableTreeNode stats = new DefaultMutableTreeNode("Statistics");
        // teach, chit chat, friendship
        DefaultMutableTreeNode event1 = new DefaultMutableTreeNode("Friends");
//        DefaultMutableTreeNode event2 = new DefaultMutableTreeNode("???");
        DefaultMutableTreeNode libEvent = new DefaultMutableTreeNode("Library");
        root.add(stats);
        root.add(stats);
        root.add(event1);
//        root.add(event2);
        root.add(libEvent);

        JTree tree = new JTree(root);
        tree.setSelectionRow(0);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if (root.equals(lastPathComponent)) {
                    pane.setRightComponent(new PanelIntroduction());
                    pane.setDividerLocation(150);
//                    pane.setRightComponent(new EventPanel());
                } else if (stats.equals(lastPathComponent)) {
                    // TODO: 2021/5/27 Debug
                    pane.setRightComponent(new BoxStudentInfo(20,students));
//                    pane.setBackground(new Color(0x7979F1));
//                    pane.setRightComponent(new EventPanel());
                    pane.setDividerLocation(150);
                } else if (event1.equals(lastPathComponent)) {
                    pane.setRightComponent(new BoxFriend(students,Student.getCurrentStudentId()));
                    pane.setDividerLocation(150);
                } /*else if (event2.equals(lastPathComponent)) {
                    pane.setRightComponent(new JLabel("wt"));
                    pane.setDividerLocation(150);
                }*/ else if (libEvent.equals(lastPathComponent)) {
                    pane.setRightComponent(new BoxLib());
                    pane.setDividerLocation(150);
                }

            }
        });
        pane.setLeftComponent(tree);
        pane.getLeftComponent().setBackground(new Color(0xB5CCEE));

        pane.setRightComponent(new PanelIntroduction());


        add(pane);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
