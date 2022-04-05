package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.swing.filechooser.*;


public class GUI implements ActionListener {
    JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    JButton btn1 = new JButton("Submit");
    JButton btn2 = new JButton("Open");
    JTextField textField = new JTextField();

    JLabel jLabel;
    JTextArea jTextArea;
    String path;

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
       btn2.addActionListener(this);

       jLabel = new JLabel("No file selected");

       panel1.add(btn1);
       panel1.add(btn2);
       panel1.add(jLabel);

       jTextArea = new JTextArea(25,30);
       jTextArea.setVisible(false);
       jTextArea.setLocation(30,110);
       jTextArea.setSize(270,200);
       jTextArea.setBackground(new Color(255,255,240));
       panel1.add(jTextArea);

       frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            // TODO: add checks here.
            SearchFiles sf = new SearchFiles(path);

            try {
                HashMap<String, Integer> occurrences = sf.Search(textField.getText());
                String s = String.format("\nThe word %s appears in the following:\n", textField.getText());
                jTextArea.append(s);

                occurrences.forEach((key, value) -> {
                    String formattedString = String.format("%s - %ox\n", key, value);
                    jTextArea.append(formattedString);
                });

            } catch (FileNotFoundException fileNotFoundException) {
                // TODO: handle whenever file is not found.
                System.out.println("File is not found.");
            }

            jTextArea.setVisible(true);
        } else if (e.getSource() == btn2) {
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = jFileChooser.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected directory
                String selectedPath = jFileChooser.getSelectedFile().getAbsolutePath();
                jLabel.setText(selectedPath);
                path = selectedPath;

                return;
            }

            jLabel.setText("The user cancelled the operation");
        }
    }
}
