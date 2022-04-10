package test;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.filechooser.*;




public class GUI implements ActionListener {
    JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    JButton btn1 = new JButton("Submit");
    JButton btn2 = new JButton("Open");
    JButton btn3 = new JButton("Clear");
    JTextField textField = new JTextField();
    JFrame frame;
    JCheckBox checkBox1;
    JCheckBox checkBox2;
    JCheckBox checkBox3;
    JCheckBox checkBox4;

    JLabel jLabel;
    JTextArea jTextArea;
    String path;
    JScrollPane scroll;

    public GUI() {
        run();
    }

   public void run() {
       // TODO: make icon work, and in the future it must be in the resources folder.
       Icon icon = new ImageIcon("C:\\Users\\gamin\\SearchEngine\\src\\test\\icons");

       JFrame frame = new JFrame("Search Engine");
       frame.setVisible(true);
       frame.setSize(400,550);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel panel1 = new JPanel();
       panel1.setBackground(Color.WHITE);
       frame.add(panel1);

       JLabel lb = new JLabel("Type in a word: ");
       lb.setBackground(Color.yellow);

       textField.setBounds(355, 0, 2000, 30);
      // textField.getMaximumSize(new Dimension(100,50));
       //textField.setText();
       textField.setPreferredSize(new Dimension(100,20));
       panel1.add(textField);

       btn1.addActionListener(this);
       btn2.addActionListener(this);
       btn3.addActionListener(this);
       btn1.setIcon(icon);

       jLabel = new JLabel("No file selected");

       panel1.add(btn1);
       panel1.add(btn3);
       panel1.add(btn2);
       panel1.add(jLabel);



       jTextArea = new JTextArea(25,30);
       jTextArea.setVisible(true);
       jTextArea.setLocation(30,110);
       jTextArea.setSize(270,200);
       jTextArea.setBackground(new Color(255,255,240));
       jTextArea.setLineWrap(true);
       jTextArea.setWrapStyleWord(true);
       scroll = new JScrollPane(jTextArea);
       //panel1.add(jTextArea);
       panel1.add(scroll);

       checkBox1 = new JCheckBox("File 1");
       checkBox1.setBounds(100,100, 50,50);
       checkBox2 = new JCheckBox("File 2");
       checkBox2.setBounds(100,100, 50,50);
       checkBox3 = new JCheckBox("File 3");
       checkBox3.setBounds(100,100, 50,50);
       checkBox4 = new JCheckBox("File 4");
       checkBox4.setBounds(100,100, 50,50);

       panel1.add(checkBox1);
       panel1.add(checkBox2);
       panel1.add(checkBox3);
       panel1.add(checkBox4);


       frame.setVisible(true);




    }

   /* public void initialiseSpellChecker(){
        String userDictionaryPath = "\\dictionary";
        SpellChecker.setUserDictionaryProvider(new FileUserDictionary(userDictionaryPath));
        SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath), "en");
        SpellChecker.register(textField);
        SpellChecker.register(jTextArea);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            if(textField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please enter a search query");
                return;
            }
            if(path == null){
                JOptionPane.showMessageDialog(frame, "Please select a path");
                return;
            }
            SearchFiles sf;
            sf = new SearchFiles(path);
            try {
                HashMap<String, Integer> occurrences = sf.Search(textField.getText());
                String s = String.format("\nThe word %s appears in the following:\n", textField.getText());
                jTextArea.append(s);

                occurrences.forEach((key, value) -> {
                    String formattedString = String.format("%s - %ox\n", key, value);
                    jTextArea.append(formattedString);
                });

            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(frame, fileNotFoundException.getMessage());
            }

            //jTextArea.setVisible(true);
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
        } else if(e.getSource() == btn3){
            textField.setText("");
        }
    }
}
