package com.sociopath.component;

import com.sociopath.events.E4ArrangeBook;
import com.sociopath.events.E6Friendship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Stack;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/6/6 22:08, in project com.sociopath.component
 */
public class PanelFriendship extends JPanel {
    public static void main(String[] args) {
        myFrame frame = new myFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private int[][] friendships;

    // display textField
    private JTextField result;
    private JTextField first;
    private JTextField second;
    private JTextField AND;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private String clickButton;

    public PanelFriendship() {
        friendships = new int[0][2];

        setLayout(new BorderLayout());

        //把文本框加在上面north里
        result = new JTextField("");

        result.setEditable(false);


        //listener
        ActionListener command = new commandAction();

        //panel1 numbers
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(6, 3));

        first = new JTextField("", JLabel.CENTER);
        first.setEditable(false);
        AND = new JTextField("", JLabel.CENTER);
        AND.setEditable(false);
        second = new JTextField("", JLabel.CENTER);
        second.setEditable(false);

        panel1.add(first);
        panel1.add(AND);
        panel1.add(second);

        addButton("1", command);
        addButton("2", command);
        addButton("3", command);

        addButton("4", command);
        addButton("5", command);
        addButton("6", command);

        addButton("7", command);
        addButton("8", command);
        addButton("9", command);

        addButton("Clear", command);
        addButton("10", command);
        addButton("Calculate", command);

        addButton("Reset", command);
        addButton("AND", command);
        addButton("Add", command);

        add(panel1, BorderLayout.CENTER);

        add(result, BorderLayout.SOUTH);

    }

    //panel1
    public void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.setBackground(Color.WHITE);
        button.addActionListener(listener);
        panel1.add(button);
    }

    //click
    class commandAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            //
            clickButton = event.getActionCommand();

            if (clickButton != "Clear" && clickButton != "AND" && clickButton != "Add" && clickButton != "Calculate" && clickButton != "Reset") {

                if (AND.getText().equals("")) {
                    first.setText("" + clickButton);
                } else if (first != null || !first.getText().equals("")) {
                    second.setText("" + clickButton);
                }


            }

            if (clickButton.equals("Calculate")) {
                String s = calculate();
                JOptionPane.showMessageDialog(null, s, "Friendship", JOptionPane.INFORMATION_MESSAGE);


            } else if (clickButton.equals("Add")) {
                if (!first.getText().equals("") && !second.getText().equals("")) {
                    int n1 = Integer.parseInt(first.getText());
                    int n2 = Integer.parseInt(second.getText());

                    if (friendships == null || friendships.length == 0) {
                        friendships = new int[][]{{n1, n2}};
                    } else {
                        friendships = Arrays.copyOf(friendships, friendships.length + 1);
                        {
                            result.setText(Arrays.deepToString(friendships));
                            friendships[friendships.length - 1] = new int[]{n1, n2};
                            friendships[friendships.length - 1][0] = n1;
                            friendships[friendships.length - 1][1] = n2;
                        }
                    }
                    System.out.println("Add new friendship: " + n1 + " - " + n2);
                    first.setText("");
                    second.setText("");
                    AND.setText("");
                    result.setText(Arrays.deepToString(friendships));
                }
            } else if (clickButton.equals("Clear")) {
                first.setText("");
                second.setText("");
                AND.setText("");


            } else if (clickButton.equals("Reset")) {
                first.setText("");
                second.setText("");
                AND.setText("");
                result.setText("");
                friendships = new int[0][2];
                System.out.println("You have reset the friendship.");
            } else if (clickButton.equals("AND")) {
                if (first.getText() != null && !first.getText().equals("")) {
                    AND.setText("AND");
                }
            }
        }
    }

    public String calculate() {
        int v = friendships.length;
        int[][] friendshipArr = friendships.clone();
        E6Friendship e6 = new E6Friendship(v);
//        String s = E6Friendship.callE6(v, friendshipArr);
//        System.out.println(s);
//        return s;
        return null;
    }
}


class myFrame extends JFrame {
    public myFrame() {
        setTitle("Friendship Calculator");
        add(new PanelFriendship());
        pack();
    }
}