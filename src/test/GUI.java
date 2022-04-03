package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{

    Icon icon = new ImageIcon("C:\\search.png");
    private JFrame frame = new JFrame("Search");
    private JTextField search = new JTextField();
    private JButton searchButton = new JButton("Search", icon);
    //private JTextArea searchResult = new JTextArea();

    public void run() {
        frame.setSize(300, 500);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        search.setBounds(850, 50, 200, 30);
       // search.setVisible(true);
       // search.setLayout(null);
        searchButton.setBounds(100,50,800,100);
       // searchButton.setVisible(true);
        //searchButton.setIcon(new ImageIcon("C:\\search.png"));
        icon.getIconHeight()
        frame.add(searchButton);
        frame.add(search);
    }

}
