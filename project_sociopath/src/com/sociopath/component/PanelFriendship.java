package com.sociopath.component;

import com.sociopath.events.E6Friendship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Description:
 *
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
                System.out.println(s);
                JOptionPane.showMessageDialog(null, s, "Friendship", JOptionPane.INFORMATION_MESSAGE);


            } else if (clickButton.equals("Add")) {
                if (!first.getText().equals("") && !second.getText().equals("")) {
                    int n1 = Integer.parseInt(first.getText());
                    int n2 = Integer.parseInt(second.getText());

                    int min = Math.min(n1,n2);
                    int max = Math.max(n1,n2);

                    if(min==max) {
                        System.out.println(min+" - "+max+" is invalid friendship!");
                        first.setText("");
                        second.setText("");
                        AND.setText("");
                    } else {

                        if (friendships == null || friendships.length == 0) {
                            friendships = new int[][]{{min, max}};

                            System.out.println("Add new friendship: " + min + " - " + max);
                            first.setText("");
                            second.setText("");
                            AND.setText("");
                            result.setText(Arrays.deepToString(friendships));

                        } else {
                            boolean exist = false;
                            for (int[] friendship : friendships) {
                                if(min==friendship[0]&&max==friendship[1])
                                {
                                    exist = true;
                                }
                            }

                            if(!exist) {
                                friendships = Arrays.copyOf(friendships, friendships.length + 1);
                                {
                                    result.setText(Arrays.deepToString(friendships));
                                    friendships[friendships.length - 1] = new int[]{min, max};
//                                friendships[friendships.length - 1][0] = min;
//                                friendships[friendships.length - 1][1] = max;
                                }

                                System.out.println("Add new friendship: " + min + " - " + max);
                                first.setText("");
                                second.setText("");
                                AND.setText("");
                                result.setText(Arrays.deepToString(friendships));

                            } else {
                                first.setText("");
                                second.setText("");
                                AND.setText("");
                                System.out.println("Friendship "+min+" - "+max+" already exists!");
                            }


                        }

                    }
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
        String s = E6Friendship.callE6(friendships);
        return s;
    }
}


class myFrame extends JFrame {
    public myFrame() {
        setTitle("Friendship Calculator");
        add(new PanelFriendship());
        pack();
    }
}