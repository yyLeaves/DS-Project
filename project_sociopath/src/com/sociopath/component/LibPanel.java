package com.sociopath.component;

import com.sociopath.util.PathUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/27 21:45, in project com.sociopath.component
 */
public class LibPanel extends JPanel {
    private Image image;
    public LibPanel() {
        try {
            // image prefer size 850 * 600
            image = ImageIO.read(new File(PathUtils.getPath("library_bg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel northPanel = new JPanel();
        JTextField noOfBooks = new JTextField(5);
        JLabel jLabel = new JLabel("books");
        JButton startButton = new JButton("Start");
        startButton.setBackground(new Color(0xF4F5DE));
        northPanel.setBackground(new Color(0xD5E7F3));
        northPanel.add(noOfBooks);
        northPanel.add(jLabel);
        northPanel.add(startButton);

        add(northPanel);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noOfBooksText = noOfBooks.getText();
                int bookNumber;
                try{
                    Integer.parseInt(noOfBooksText);
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
                System.out.println(noOfBooksText);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(400,500,1000,600);
        frame.add(new LibPanel());

        frame.setVisible(true);
    }

    private class BookTable extends JTable{

    }
}
