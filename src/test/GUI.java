package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GUI extends JFrame implements ActionListener{



   /* public void run() {
        frame.setSize(300, 500);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        search.setBounds(85, 50, 200, 30);
       // search.setVisible(true);
       // search.setLayout(null);
        searchButton.setBounds(100,100,80,10);
       // searchButton.setVisible(true);
        //searchButton.setIcon(new ImageIcon("C:\\search.png"));

        frame.add(searchButton);
        frame.add(search);


    }*/
    JLabel lb = new JLabel();
    JTextField textField = new JTextField();
    Icon icon = new ImageIcon("C:\\srch.png");
    JFrame frame = new JFrame();
    JButton btn1 = new JButton("Submit");


   public void run(){

       textField.setBounds(0, 0, 200, 30);

       btn1.setBounds(315, 50, 100, 30);
       btn1.setBackground(new Color(173, 37, 51));
       btn1.setForeground(Color.white);
       btn1.setOpaque(true);
       btn1.setBorderPainted(false);
       btn1.setVisible(true);
       btn1.addActionListener(this);

       frame.setTitle("Search Engine");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //frame.setResizable(false); //prevent frame from being resize
       frame.setSize(850,420);
       //frame.setResizable(false);
       frame.setVisible(true);
       frame.setLayout(null);

       ImageIcon image = new ImageIcon("C:\\logo.png");
       frame.setIconImage(image.getImage()); //Change icon of frame
       frame.getContentPane().setBackground(new Color(0x240115));

       frame.add(lb);
       frame.add(textField);
       frame.add(btn1);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btn1){
           System.out.println(textField.getText());
           SearchFiles sf = null;
           sf = new SearchFiles();
           sf.Search();
       }

    }
}
