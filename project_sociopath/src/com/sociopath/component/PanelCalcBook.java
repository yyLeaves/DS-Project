package com.sociopath.component;

import com.sociopath.events.E4ArrangeBook;

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
 * Created on: 2021/6/6 16:07, in project com.sociopath.component
 */


public class PanelCalcBook extends JPanel {
    public static void main(String[] args) {
        BookCalcFrame frame = new BookCalcFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    Stack<Integer> books;

    // display textField
    private JTextField display;
    private JTextField result;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private String clickButton;

    public PanelCalcBook() {

        books = new Stack<>();

        setLayout(new BorderLayout());

        //把文本框加在上面north里
        display = new JTextField("");
        result = new JTextField("");
        display.setEditable(false);
        result.setEditable(false);

//        display.setEnabled(true);
        add(display, BorderLayout.NORTH);

        //listener
        ActionListener command = new commandAction();

        //panel1 numbers
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 3));

        addButton("7", command);
        addButton("8", command);
        addButton("9", command);

        addButton("4", command);
        addButton("5", command);
        addButton("6", command);

        addButton("1", command);
        addButton("2", command);
        addButton("3", command);

        addButton("Backspace", command);
        addButton("0", command);
        addButton("Arrange", command);

        addButton("Pop",command);
        addButton("Reset",command);
        addButton("Push",command);

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

    // panel 2
    private void addButton2(String label, ActionListener listener) {
        //
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel2.add(button);
    }

    //click
    class commandAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            //
            clickButton = event.getActionCommand();

            if (clickButton != "Backspace" && clickButton != "Push" && clickButton != "Pop" && clickButton != "Arrange" && clickButton != "Reset") {

                display.setText(display.getText() + clickButton);
            }

            if (clickButton.equals("Arrange")) {
                if (books != null && !books.isEmpty()) {
                    String resultString = calculate();
                    result.setText(resultString);
                } else {
                    System.out.println("No books can be arranged");
                }

            } else if (clickButton.equals("Backspace")) {
                if (display.getText() != null && !display.getText().equals("")) {
                    //back one space
                    StringBuffer sb = new StringBuffer(display.getText());
                    display.setText(sb.substring(0, sb.length() - 1));
                }
                if(books!=null&&!books.isEmpty()) {
                    result.setText(Arrays.toString(books.toArray()));
                }
            } else if (clickButton.equals("Push")) {
                if (display.getText() != null && !display.getText().equals("")) {
                    //clear
                    StringBuffer sb = new StringBuffer(Integer.parseInt(display.getText()));
                    books.add(Integer.parseInt(display.getText()));
                    System.out.print("Push book " + books.size() + ": ");
                    System.out.println(display.getText());
                    display.setText("");
                    result.setText(Arrays.toString(books.toArray()));
                } else {
                    System.out.println("Nothing can be pushed");
                }

            } else if (clickButton.equals("Pop")) {
                if (books != null && !books.isEmpty()) {

                    Integer pop = books.pop();
                    display.setText("");
                    result.setText(Arrays.toString(books.toArray()));
                    System.out.println("Pop out book " + (1 + books.size()) + ": " + pop);

                } else {
                    System.out.println("Currently no book can be popped out!");
                }
            } else if (clickButton.equals("Reset")) {
                books = new Stack<>();
                display.setText("");
                result.setText("");
                System.out.println("You have reset the books.");
            }
        }
    }

    public String calculate() {
        E4ArrangeBook e4 = new E4ArrangeBook();
        int num = books.size();
        Integer[] bookHeight = books.toArray(new Integer[num]);

        String arrange = e4.arrange(num, bookHeight);
        System.out.println(arrange);
        return arrange;
    }
}


class BookCalcFrame extends JFrame {
    public BookCalcFrame() {
        setTitle("Arrange Calculator");
        add(new PanelCalcBook());
        pack();
    }
}