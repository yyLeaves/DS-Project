package com.sociopath.ui;

import com.sociopath.component.EventPanel;
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

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/13 17:10, in project com.sociopath.ui
 */
public class EventsInterface extends JFrame {

    final int WIDTH = 1000;
    final int HEIGHT = 600;

    public static void main(String[] args) throws IOException {
        new EventsInterface().init();
    }

    public void init() throws IOException {
        this.setTitle("Hello, User!");
        setBounds(ScreenUtils.getScreenWidth()/2 - WIDTH/2, ScreenUtils.getScreenHeight()/2 - HEIGHT/2,WIDTH,HEIGHT);
        setResizable(false);
        setIconImage(ImageIO.read(new File(PathUtils.getPath("event_icon_image"))));

        // set menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu settingMenu = new JMenu("Settings");
        JMenuItem switchItem = new JMenuItem("Switch");
        JMenuItem exitItem = new JMenuItem("Exit");
        switchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface().init();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                dispose();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        settingMenu.add(switchItem);
        settingMenu.add(exitItem);
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
        DefaultMutableTreeNode initSetting = new DefaultMutableTreeNode("Initialize");
        DefaultMutableTreeNode event1 = new DefaultMutableTreeNode("Teach Stranger");
        DefaultMutableTreeNode event2 = new DefaultMutableTreeNode("Chit Chat");
        root.add(initSetting);
        root.add(initSetting);
        root.add(event1);
        root.add(event2);

        JTree tree = new JTree(root);
        tree.setSelectionRow(0);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if(root.equals(lastPathComponent)) {
                    pane.setRightComponent(new JLabel("Powered by Anyname"));
                    pane.setDividerLocation(150);
//                    pane.setRightComponent(new EventPanel());
                } else if(initSetting.equals(lastPathComponent)) {
                    pane.setRightComponent(new JLabel("To be implemented 0"));
//                    pane.setBackground(new Color(0x7979F1));
                    pane.setRightComponent(new EventPanel());
                    pane.setDividerLocation(150);
                } else if(event1.equals(lastPathComponent)) {
                    pane.setRightComponent(new JLabel("To be implemented 1"));
                    pane.setDividerLocation(150);
                } else if(event2.equals(lastPathComponent)) {
                    pane.setRightComponent(new JLabel("To be implemented 2"));
                    pane.setDividerLocation(150);
                }

            }
        });
        pane.setLeftComponent(tree);
        pane.setRightComponent(new JLabel("Powered by Anyname"));


        add(pane);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
