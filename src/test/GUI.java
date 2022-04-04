package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class GUI implements ActionListener {
    JTextArea jt;
    JButton btn1 = new JButton("Submit");
    JTextField textField = new JTextField();

    public GUI() {
        run();
    }

   public void run() {
       // TODO: make icon work, and in the future it must be in the resources folder.
       // Icon icon = new ImageIcon("C:\\srch.png");

       JFrame frame = new JFrame("Search Engine");
       frame.setVisible(true);
       frame.setSize(1080,720);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel panel1 = new JPanel();
       panel1.setBackground(Color.WHITE);
       frame.add(panel1);

       JLabel lb = new JLabel("Type in a word: ");
       lb.setBackground(Color.yellow);

       textField.setBounds(355, 0, 2000, 30);
       textField.setText("Enter Your Words: ");
       panel1.add(textField);

       btn1.addActionListener(this);
       panel1.add(btn1);

       jt = new JTextArea(25,30);
       jt.setVisible(false);
       jt.setLocation(30,110);
       jt.setSize(270,200);
       jt.setBackground(new Color(255,255,240));
       panel1.add(jt);

       frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btn1){
           // TODO: add checks here.
           SearchFiles sf = new SearchFiles();

           try {
               HashMap<String, Integer> occurrences = sf.Search(textField.getText());
               //System.out.println(occurrences);
               /*
                   TODO: show in ui, and make it nice (use the element
                    that you've just made and display them there with a for loop)
               */

           } catch (FileNotFoundException fileNotFoundException) {
               // TODO: handle whenever file is not found.
               System.out.println("File is not found.");
           }

           jt.setVisible(true);
       }
    }
}
